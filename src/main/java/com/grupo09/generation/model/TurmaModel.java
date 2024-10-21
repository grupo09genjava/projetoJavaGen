package com.grupo09.generation.model;

import java.util.List;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tab_turmas")
public class TurmaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    private String nome;

    @NotBlank(message = "Instrutor é obrigatório")
    @Size(min = 3, message = "Instrutor deve ter pelo menos 3 caracteres")
    private String instrutor;

    @OneToMany(mappedBy = "turmas")
    private List<AlunoModel> alunos;
}