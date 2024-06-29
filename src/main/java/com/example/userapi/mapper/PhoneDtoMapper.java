package com.example.userapi.mapper;

import com.example.userapi.dtos.PhoneDto;
import com.example.userapi.dtos.RegisterUserDto;
import com.example.userapi.persistence.entity.Phone;
import com.example.userapi.persistence.entity.User;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PhoneDtoMapper {
    // entity -> dto
    @Mappings({
            @Mapping(source = "number", target = "number"),
            @Mapping(source = "cityCode", target = "cityCode"),
            @Mapping(source = "countryCode", target = "countryCode"),
            //@Mapping(source = "user", target = "registerUserDto"),
            @Mapping(target = "registerUserDto", ignore = true),
    })
    PhoneDto toPhoneDto(Phone phone);
    List<PhoneDto> toPhoneDtos(List<Phone> phones);

    // dto -> entity
    @InheritInverseConfiguration
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "phoneId", ignore = true)
    Phone toPhone(PhoneDto phoneDto);
}
