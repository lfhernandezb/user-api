package com.example.userapi.web.controller;

import com.example.userapi.dtos.RegisterUserDto;
import com.example.userapi.service.UserService;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/users")
@RestController
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @GetMapping("/me")
    public ResponseEntity<UserDetails> authenticatedUser() {
        logger.info("authenticatedUser invoked");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        return ResponseEntity.ok(userDetails);
    }

    @GetMapping("/all")
    public ResponseEntity<List<RegisterUserDto>> allUsers() {
        logger.info("allUsers invoked");
        List <RegisterUserDto> users = userService.getAll();

        return ResponseEntity.ok(users);
    }
}