package com.example.userapi.repository;

import com.example.userapi.dtos.PhoneDto;

public interface PhoneDtoRepository {
    PhoneDto save(PhoneDto phoneDto);
}
