package com.example.userapi.service;

import com.example.userapi.dtos.LoginUserDto;
import com.example.userapi.dtos.RegisterUserDto;
import com.example.userapi.persistence.RegisterUserDtoRepositoryImpl;
import com.example.userapi.persistence.crud.UserCrudRepository;
import com.example.userapi.persistence.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private UserService userService;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserService userService,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    public RegisterUserDto signup(RegisterUserDto input) {
        return userService.save(input);
    }

    public RegisterUserDto authenticate(LoginUserDto input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getEmail(),
                        input.getPassword()
                )
        );

        return userService.getByEmail(input.getEmail());
    }
}