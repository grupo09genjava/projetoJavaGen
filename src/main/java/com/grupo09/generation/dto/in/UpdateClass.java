package com.grupo09.generation.dto.in;

import jakarta.validation.constraints.NotBlank;

public record UpdateClass(
        @NotBlank(message = "Name is required.")
        String name,

        @NotBlank(message = "Instructor is required.")
        String instructor
) {
}

