package com.example.web.controller;

import com.example.web.dto.UserLoginRequestDto;
import com.example.web.dto.UserLoginResponseDto;
import com.example.web.dto.UserRegistrationRequestDto;
import com.example.web.dto.UserRegistrationResponseDto;
import com.example.web.exception.RegistrationException;
import com.example.web.security.AuthenticationService;
import com.example.web.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Authenticate management", description = "Endpoints for managing authentication")
@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    @Operation(summary = "User Login",
            description = "Authenticate a user with their login credentials")
    @PostMapping("/login")
    public UserLoginResponseDto login(@RequestBody @Valid UserLoginRequestDto userLoginRequestDto) {
        return authenticationService.authenticate(userLoginRequestDto);
    }

    @Operation(summary = "User Registration",
            description = "Register a new user with its provided details")
    @PostMapping("/register")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public UserRegistrationResponseDto register(
            @RequestBody @Valid UserRegistrationRequestDto userRegistrationRequestDto)
            throws RegistrationException {
        return userService.register(userRegistrationRequestDto);
    }

    @Operation(summary = "User delete",
            description = "Deletes an user by its ID.")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @Operation(summary = "Get all users",
            description = "Return a list of all users")
    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<UserRegistrationResponseDto> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }
}
