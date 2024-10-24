package com.grupo09.generation.dto.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginEmployee(
        @Email(message = "Enter a valid email in English.") @NotBlank(message = "The email is required.") String email,
        @NotBlank(message = "The password field is required.") @Size(min = 6, max = 100, message = "The password must be at least 6 characters long.") String password){
}
