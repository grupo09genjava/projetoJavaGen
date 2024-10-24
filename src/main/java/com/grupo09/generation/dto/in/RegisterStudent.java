package com.grupo09.generation.dto.in;

import jakarta.validation.constraints.*;

public record RegisterStudent(
        @NotBlank(message = "The student name is required.")
        @Size(min = 3, max = 100, message = "Name must be at least 3 characters.")
        String name,
        @Email(message = "Enter a valid email.")
        @NotBlank(message = "The email is required.")
        String email,
        @NotNull(message = "Age is required.")
        @Positive(message = "Age must be a positive number.")
        Integer age
){
}
