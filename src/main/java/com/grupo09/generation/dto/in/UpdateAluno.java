package com.grupo09.generation.dto.in;

import jakarta.validation.constraints.*;

import java.util.Optional;

public record UpdateAluno(
        @NotBlank(message = "O nome é obrigatório")
        @Size(min = 3, max = 100, message = "O campo nome deve ter no mínimo 3 caracteres")
        String nome,
        @Email(message = "Insira um email válido")
        @NotBlank(message = "O email é obrigatório")
        String email,
        @NotNull(message = "A idade é obrigatória")
        @Positive(message = "A idade deve ser um número positivo")
        Integer idade,
        @Max(value = 10, message = "A nota do primeiro módulo deve ser no máximo 10")
        Double notaPrimeiroModulo,
        @Max(value = 10, message = "A nota do primeiro módulo deve ser no máximo 10")
        Double notaSegundoModulo
){
}
