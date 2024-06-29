package com.example.userapi.repository;

import com.example.userapi.dtos.RegisterUserDto;

import java.util.List;
import java.util.UUID;

public interface RegisterUserDtoRepository {
    RegisterUserDto save(RegisterUserDto userDto);
    RegisterUserDto findByEmail(String email);
    RegisterUserDto findById(UUID userId);
    List<RegisterUserDto> findAll();
    void deleteById(UUID userId);
}
