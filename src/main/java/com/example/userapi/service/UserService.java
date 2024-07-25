package com.example.userapi.service;

import com.example.userapi.exception.*;
import com.example.userapi.persistence.RegisterUserDtoRepositoryImpl;
import com.example.userapi.persistence.crud.PhoneCrudRepository;
import com.example.userapi.persistence.crud.UserCrudRepository;
import com.example.userapi.persistence.entity.User;
import com.example.userapi.dtos.RegisterUserDto;
import com.example.userapi.repository.PhoneDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

;

@Service
public class UserService {

    @Value("${validation.password}")
    private String passwordPattern;

    private final Logger logger = Logger.getLogger(UserService.class.getName());

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Autowired
    private RegisterUserDtoRepositoryImpl registerUserDtoRepository;

    @Autowired
    private PhoneDtoRepository phoneDtoRepository;

    @Autowired
    private JwtService jwtService;

    public List<RegisterUserDto> getAll() {
        return registerUserDtoRepository.findAll();
    }

    public RegisterUserDto getById(UUID id) {
        logger.info("getById called");
        return registerUserDtoRepository.findById(id);
    }

    public RegisterUserDto getByEmail(String email) {

        return registerUserDtoRepository.findByEmail(email);
    }

    @Transactional
    public RegisterUserDto save(RegisterUserDto user) {
        /*
        if (user.getEmail() != null && !user.getEmail().isEmpty()) {
            if (!validateEmail(user.getEmail())) {
                throw new NameNotProvidedException("El email no es valido");
            }
        } else {
            throw new NameNotProvidedException("El email no puede ser nulo");
        }

        if (passwordPattern == null || passwordPattern.isEmpty()) {
            throw new MissingConfigurationValueException("No se encuentra el pattern de contrasenias en la configuracion");
        }

        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new PasswordNotProvidedException("La contrasenia no puede ser nula");
        }

        Pattern pattern = Pattern.compile(passwordPattern);

        logger.info("password parttern: " + passwordPattern);

        Matcher matcher = pattern.matcher(user.getPassword());

        if (!matcher.matches()) {
            throw new InvalidPasswordException("La contrasenia no es valida");
        }

        if (user.getName() == null || user.getName().isEmpty()) {
            throw new NameNotProvidedException("El nombre no puede ser nulo");
        }

        if (user.getPhones() == null || user.getPhones().isEmpty()) {
            throw new PhoneNotProvidedException("Debe proveer al menos un telefono");
        }
        */
        Date date = new Date();

        if (user.getUserId() == null) {
            // usuario nuevo

            // valido que no exista un usuario con el email recibido
            if (getByEmail(user.getEmail()) != null) {
                throw new EmailAlreadyRegisteredException("El correo ya esta registrado");
            }

            user.setUserId(UUID.randomUUID());
            user.setCreated(date);
            user.setLastLogin(date);
            user.setIsActive(true);

            // obtengo token
            user.setToken(jwtService.generateToken(user));

        } else {
            // actualizacion
            // verificamos que el usuario exista buscando por email
            RegisterUserDto userDTO = getByEmail(user.getEmail());

            if (userDTO != null) {
                user.setModified(date);
            } else {
                throw new UserNotFoundException("Usuario no encontrado para actualizar");
            }
        }

        System.out.println("UserDTO: " + user.toString());

        RegisterUserDto ret = registerUserDtoRepository.save(user);
        /*
        user.getPhones().forEach(phone -> {

            phone.setRegisterUserDto(ret);
            logger.info("phone: " + phone.toString());
            phoneDtoRepository.save(phone);
        });
         */
        return ret;
    }

    @Transactional
    public boolean delete(UUID id) {
        logger.info("delete called");
        //RegisterUserDto registerUserDto = getById(id);
        registerUserDtoRepository.deleteById(id);
        return true;
    }

    private boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.matches();
    }
}
