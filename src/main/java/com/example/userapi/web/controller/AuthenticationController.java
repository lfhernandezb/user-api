package com.example.userapi.web.controller;

import com.example.userapi.dtos.RegisterUserDto;
import com.example.userapi.dtos.LoginUserDto;
import com.example.userapi.service.AuthenticationService;
import com.example.userapi.service.JwtService;
import com.example.userapi.responses.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<RegisterUserDto> register(@Valid @RequestBody RegisterUserDto registerUserDto) {
        RegisterUserDto registerUserDto1 = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registerUserDto1);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@Valid @RequestBody LoginUserDto loginUserDto) {
        RegisterUserDto authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(jwtService.getExpirationTime())
                .build();//new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}