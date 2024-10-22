package com.grupo09.generation.model;

import com.grupo09.generation.dto.in.CreateAluno;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

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
    @Column(nullable = false)
    private String nome;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(nullable = false)
    private Integer idade;
    private Double notaPrimeiroModulo;
    private Double notaSegundoModulo;
    private Double media;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "turma_id")
    private TurmaModel turma;

    public AlunoModel(String nome,String email,Integer idade){
        this.nome = nome;
        this.email = email;
        this.idade = idade;
        this.notaPrimeiroModulo = null;
        this.notaSegundoModulo = null;
        this.media = null;
    }

    public Optional<Double> getMedia(){
        return Optional.ofNullable(media);
    }

    public Optional<Double> getNotaSegundoModulo(){
        return Optional.ofNullable(notaPrimeiroModulo);
    }

    public Optional<Double> getNotaPrimeiroModulo(){
        return Optional.ofNullable(notaSegundoModulo);
    }

    public void atualizarMedia() {
        this.media = calculateMedia();
    }

    public static AlunoModel toEntity(CreateAluno createAluno){
        return new AlunoModel(
                createAluno.nome(),
                createAluno.email(),
                createAluno.idade()
        );
    }

    private Double calculateMedia(){
        double result = (this.notaPrimeiroModulo + this.notaSegundoModulo) / 2;
        return Math.round(result * 10.0) / 10.0;
    }
}
