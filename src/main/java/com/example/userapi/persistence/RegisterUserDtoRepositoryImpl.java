package com.example.userapi.persistence;

import com.example.userapi.dtos.PhoneDto;
import com.example.userapi.dtos.RegisterUserDto;
import com.example.userapi.exception.UserNotFoundException;
import com.example.userapi.mapper.PhoneDtoMapper;
import com.example.userapi.mapper.RegisterUserDtoMapper;
import com.example.userapi.persistence.crud.PhoneCrudRepository;
import com.example.userapi.persistence.crud.UserCrudRepository;
import com.example.userapi.persistence.entity.User;
import com.example.userapi.repository.RegisterUserDtoRepository;
import lombok.extern.flogger.Flogger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Repository
public class RegisterUserDtoRepositoryImpl implements RegisterUserDtoRepository {

    private final Logger logger = LoggerFactory.getLogger(RegisterUserDtoRepositoryImpl.class);

    @Autowired
    private UserCrudRepository userCrudRepository;

    @Autowired
    private PhoneCrudRepository phoneCrudRepository;

    @Autowired
    private RegisterUserDtoMapper registerUserDtoMapper;

    @Autowired
    private PhoneDtoMapper phoneDtoMapper;

    @Override
    public RegisterUserDto save(RegisterUserDto registerUserDto) {

        logger.info("to persist RegisterUserDto: " + registerUserDto.toString());

        //User user = userCrudRepository.save(registerUserDtoMapper.toUser(registerUserDto));

        User user = registerUserDtoMapper.toUser(registerUserDto);

        user.getPhones().forEach(phone -> phone.setUser(user));

        System.out.println("User: " + user.toString());

        User u = userCrudRepository.save(user);

        logger.info("persisted User: " + u.toString());

        /*
        List<PhoneDto> phones = registerUserDto.getPhones();

        //RegisterUserDto registerUserDto1 = registerUserDtoMapper.toUserDto(user);

        phones.forEach(phone -> {
            phone.setRegisterUserDto(registerUserDto);
            //phoneCrudRepository.save(phoneDtoMapper.toPhone(phone));
        });

        registerUserDto.setPhones(phones);

        User user = userCrudRepository.sav e(registerUserDtoMapper.toUser(registerUserDto));
        */
        return registerUserDtoMapper.toUserDto(u);
    }

    @Override
    public RegisterUserDto findByEmail(String email) {
        return userCrudRepository.findByEmail(email).map(user ->
                registerUserDtoMapper.toUserDto(user)).orElse(null); //.orElseThrow(() -> new UserNotFoundException("User not found XXX"));

    }

    @Override
    public RegisterUserDto findById(UUID userId) {
        return userCrudRepository.findById(userId).map(user ->
                registerUserDtoMapper.toUserDto(user)).orElse(null); //orElseThrow(() -> new UserNotFoundException("User not found XXX"));
    }

    @Override
    public List<RegisterUserDto> findAll() {
        List<User> list = (List<User>) userCrudRepository.findAll();

        logger.info("users list in findAll(): " + list.toString());
        return registerUserDtoMapper.toRegisterUserDtos(list);
    }

    @Override
    public void deleteById(UUID userId) {
        userCrudRepository.deleteById(userId);
    }


}
