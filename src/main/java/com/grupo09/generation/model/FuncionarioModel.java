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
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private String senha;
    @Column(nullable = false)
    private String cargo;
}
