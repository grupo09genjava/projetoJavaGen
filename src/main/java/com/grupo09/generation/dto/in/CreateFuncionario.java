package com.grupo09.generation.dto.in;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateFuncionario(
        @NotBlank(message = "O nome do funcionário é obrigatório")
        @Size(min = 3, max = 100, message = "Nome deve ter no mínimo 3 caracteres")
        String nome,
        @Email(message = "Insira um email válido")
        @NotBlank(message = "O email é obrigatório")
        String email,
        @NotBlank(message = "A campo senha é obrigatório")
        @Size(min = 6, max = 100, message = "A senha deve ter no mínimo 6 caracteres")
        String senha,
        @NotBlank(message = "O campo cargo é obrigatório")
        String cargo
) {
}
