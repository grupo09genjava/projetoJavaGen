package com.grupo09.generation.dto.out;

public record LoginOutput(
        String accessToken,
        Long expiresIn
) {
}
