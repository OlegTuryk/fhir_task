package com.example.web.dto;

import com.example.web.validation.FieldMatch;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
@FieldMatch(firstFieldName = "password", secondFieldName = "repeatPassword",
        message = "The password fields must match")
public class UserRegistrationRequestDto {
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank
    @Length(min = 8, max = 32)
    private String password;
    @NotBlank
    @Length(min = 8, max = 32)
    private String repeatPassword;
}
