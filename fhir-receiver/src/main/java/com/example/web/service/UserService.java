package com.example.web.service;

import com.example.web.dto.UserRegistrationRequestDto;
import com.example.web.dto.UserRegistrationResponseDto;
import com.example.web.exception.RegistrationException;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface UserService {
    UserRegistrationResponseDto register(UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException;

    void deleteUser(Long id);

    List<UserRegistrationResponseDto> getUsers(Pageable pageable);
}
