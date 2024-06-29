package com.example.userapi.persistence;

import com.example.userapi.dtos.PhoneDto;
import com.example.userapi.mapper.PhoneDtoMapper;
import com.example.userapi.mapper.RegisterUserDtoMapper;
import com.example.userapi.persistence.crud.PhoneCrudRepository;
import com.example.userapi.persistence.crud.UserCrudRepository;
import com.example.userapi.persistence.entity.Phone;
import com.example.userapi.persistence.entity.User;
import com.example.userapi.repository.PhoneDtoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class PhoneDtoRepositoryImpl implements PhoneDtoRepository {

    @Autowired
    private PhoneCrudRepository phoneCrudRepository;

    @Autowired
    private PhoneDtoMapper phoneDtoMapper;

    @Override
    public PhoneDto save(PhoneDto phoneDto) {
        Phone phone = phoneCrudRepository.save(phoneDtoMapper.toPhone(phoneDto));
        return phoneDtoMapper.toPhoneDto(phone);    }
}
