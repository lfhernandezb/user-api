package com.example.userapi.service;

import com.example.userapi.dtos.RegisterUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        RegisterUserDto registerUserDto = userService.getByEmail(username);

        if (registerUserDto == null) {
            throw new UsernameNotFoundException("Usuario no encontrado por email: " + username);
        }
        return registerUserDto;
    }
}
