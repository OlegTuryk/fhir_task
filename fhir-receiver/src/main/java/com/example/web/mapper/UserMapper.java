package com.example.web.mapper;

import com.example.web.config.MapperConfig;
import com.example.web.dto.UserRegistrationRequestDto;
import com.example.web.dto.UserRegistrationResponseDto;
import com.example.web.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = MapperConfig.class)
public interface UserMapper {
    UserRegistrationResponseDto toUserResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleted", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    User toModel(UserRegistrationRequestDto requestDto);
}
