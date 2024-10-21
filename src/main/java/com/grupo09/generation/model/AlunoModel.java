package com.grupo09.generation.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

@Entity
public class AlunoModel {
   
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O campo nome deve ter no mínimo 3 caracteres")
    private String nome;

    @Email(message = "email inválido")
    @NotBlank(message = "O email é obrigatório")
    @Column(unique = true)
    private String email;

    @Positive(message = "A idade deve ser um número positivo")
    private Integer idade;

    @Max(value = 10, message = "A nota do primeiro módulo deve ser no máximo 10")
    private Double notaPrimeiroModulo;

    @Max(value = 10, message = "A nota do segundo módulo deve ser no máximo 10")
    private Double notaSegundoModulo;

    private Double media;

    @ManyToOne
    @JoinColumn(name = "turma_id")  // Cria a coluna que vai guardar a referência da turma
    private TurmaModel turmas;

    
}
