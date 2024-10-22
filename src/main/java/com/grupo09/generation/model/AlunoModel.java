package com.grupo09.generation.model;

import com.grupo09.generation.dto.in.CreateAluno;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tab_alunos")
@Builder
public class AlunoModel{
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
    @JoinColumn(name = "turma_id")
    private TurmaModel turma;

    public AlunoModel(String nome, String email, Integer idade, Double notaPrimeiroModulo, Double notaSegundoModulo, Double media) {
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.notaPrimeiroModulo = notaPrimeiroModulo;
        this.notaSegundoModulo = notaSegundoModulo;
        this.media = media;
    }

    public static AlunoModel toEntity(CreateAluno createAluno) {
        return new AlunoModel(
                createAluno.nome(),
                createAluno.email(),
                createAluno.idade(),
                createAluno.notaPrimeiroModulo(),
                createAluno.notaSegundoModulo(),
                null
        );
    }
}
