package com.grupo09.generation.dto.in;

import jakarta.validation.constraints.*;

public record UpdateEmployee(
        @NotBlank(message = "Name is required.")
        String name,

        @Email(message = "Enter a valid email.")
        @NotBlank(message = "The email is required.")
        String email,

        @NotBlank(message = "Password is required.")
        String password,

        @NotBlank(message = "Job title is required.")
        String jobTitle
) {
}