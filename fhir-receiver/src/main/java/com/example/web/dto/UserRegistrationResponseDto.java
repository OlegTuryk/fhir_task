package com.example.web.dto;

public record UserRegistrationResponseDto(Long id, String email,
                                          String firstName, String lastName) {
}
