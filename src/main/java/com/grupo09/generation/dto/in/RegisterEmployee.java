package com.grupo09.generation.dto.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RegisterEmployee(
        @NotBlank(message = "The employee name is required.")
        @Size(min = 3, max = 100, message = "Name must be at least 3 characters.")
        String name,
        @Email(message = "Enter a valid email.")
        @NotBlank(message = "The email is required.")
        String email,
        @NotBlank(message = "The password is required.")
        @Size(min = 6, max = 100, message = "The password must be at least 6 characters long.")
        String password,
        @NotBlank(message = "The job title field is required.")
        String jobTitle
) {
}
