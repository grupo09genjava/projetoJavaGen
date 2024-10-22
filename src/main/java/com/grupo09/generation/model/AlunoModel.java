package com.grupo09.generation.model;

import com.grupo09.generation.dto.in.CreateAluno;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Column(name = "aluno_id")
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 3, max = 100, message = "O campo nome deve ter no mínimo 3 caracteres")
    private String nome;

    @Email(message = "email inválido")
    @NotBlank(message = "O email é obrigatório")
    @Column(unique = true)
    private String email;

    @NotNull(message = "A idade é obrigatória")
    @Positive(message = "A idade deve ser um número positivo")
    private Integer idade;

    @Max(value = 10, message = "A nota do primeiro módulo deve ser no máximo 10")
    private Double notaPrimeiroModulo;

    @Max(value = 10, message = "A nota do segundo módulo deve ser no máximo 10")
    private Double notaSegundoModulo;

    private Double media;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "turma_id")
    private TurmaModel turma;

    public AlunoModel(String nome,String email,Integer idade,Double notaPrimeiroModulo,Double notaSegundoModulo,Double media){
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.notaPrimeiroModulo = notaPrimeiroModulo;
        this.notaSegundoModulo = notaSegundoModulo;
        this.media = media;
    }

    public static AlunoModel toEntity(CreateAluno createAluno){
        double notaPrimeiroModulo = createAluno.notaPrimeiroModulo().orElse(0.0);
        double notaSegundoModulo = createAluno.notaSegundoModulo().orElse(0.0);
        double media = createAluno.media().isEmpty() ? 0.0 : calculateMedia(notaPrimeiroModulo,notaSegundoModulo);
        return new AlunoModel(
                createAluno.nome(),
                createAluno.email(),
                createAluno.idade(),
                notaPrimeiroModulo,
                notaSegundoModulo,
                media
        );
    }

    private static double calculateMedia(Double notaPrimeiroModulo,Double notaSegundoModulo){
        if (notaPrimeiroModulo != null && notaSegundoModulo != null) {
            return (notaPrimeiroModulo + notaSegundoModulo) / 2;
        } else {
            return 0.0;
        }
    }
}
