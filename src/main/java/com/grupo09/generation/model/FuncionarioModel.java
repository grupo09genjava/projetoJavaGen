package com.grupo09.generation.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tab_funcionarios")
@Builder
public class FuncionarioModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "funcionario_id")
    private Long Id;

    @NotBlank(message = "O nome do funcionário é obrigatório")
    @Size(min = 3, max = 100, message = "Nome deve ter no mínimo 3 caracteres")
    private String nome;

    @Email(message = "email inválido")
    @NotBlank(message = "O email é obrigatório")
    @Column(unique = true)
    private String email;

    @NotBlank(message = "A campo senha é obrigatório")
    @Size(min = 6, max = 100, message = "A senha deve ter no mínimo 6 caracteres")
    private String senha;

    @NotBlank(message = "O campo cargo é obrigatório")
    private String cargo;
}
