package com.grupo09.generation.dto.in;

import jakarta.validation.constraints.*;

public record UpdateStudent(
        @NotBlank(message = "Name is required.")
        @Size(min = 3, max = 100, message = "The name field must have at least 3 characters.")
        String name,

        @Email(message = "Enter a valid email.")
        @NotBlank(message = "The email is required.")
        String email,

        @NotNull(message = "Age is required.")
        @Positive(message = "Age must be a positive number.")
        Integer age,

        @Max(value = 10, message = "The score for the first module must be at most 10.")
        Double firstModuleScore,

        @Max(value = 10, message = "The score for the second module must be at most 10.")
        Double secondModuleScore
) {
}
