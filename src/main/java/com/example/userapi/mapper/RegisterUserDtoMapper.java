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

@Mapper(componentModel = "spring", uses = {PhoneDtoMapper.class})
public interface RegisterUserDtoMapper {

    // entity -> dto
    @Mappings({
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "phones", target = "phones"),
            @Mapping(source = "token", target = "token"),
            @Mapping(source = "created", target = "created"),
            @Mapping(source = "modified", target = "modified"),
            @Mapping(source = "lastLogin", target = "lastLogin"),
            @Mapping(source = "active", target = "isActive"),
            @Mapping(target = "authorities", ignore = true),
    })
    RegisterUserDto toUserDto(User user);
    List<RegisterUserDto> toRegisterUserDtos(List<User> users);

    // dto -> entity
    @Mappings({
            @Mapping(source = "userId", target = "userId"),
            @Mapping(source = "name", target = "name"),
            @Mapping(source = "email", target = "email"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "phones", target = "phones"),
            @Mapping(source = "token", target = "token"),
            @Mapping(source = "created", target = "created"),
            @Mapping(source = "modified", target = "modified"),
            @Mapping(source = "lastLogin", target = "lastLogin"),
            @Mapping(source = "isActive", target = "active"),
            // @Mapping(source = "authorities", ignore = true),
    })
    //@InheritInverseConfiguration
    //@Mapping(target = "token", ignore = true)
    User toUser(RegisterUserDto userDto);
    List<User> toUsers(List<RegisterUserDto> registerUserDtos);
}
