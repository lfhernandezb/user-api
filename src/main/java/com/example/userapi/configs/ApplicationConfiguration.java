package com.example.userapi.configs;

import com.example.userapi.persistence.RegisterUserDtoRepositoryImpl;
import com.example.userapi.configs.JwtAuthenticationFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class ApplicationConfiguration {
    private final RegisterUserDtoRepositoryImpl registerUserDtoRepository;

    public ApplicationConfiguration(RegisterUserDtoRepositoryImpl registerUserDtoRepository) {
        this.registerUserDtoRepository = registerUserDtoRepository;
    }

    /*
    como obtener el user desde la BD
     */
    @Bean
    UserDetailsService userDetailsService() {
        return username -> registerUserDtoRepository.findByEmail(username);
    }

    @Bean
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    /*
    cambiamos la estrategia de autenticacion que por defecto es HTTP BASIC a una basada en
    la BD
     */
    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();

        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

}