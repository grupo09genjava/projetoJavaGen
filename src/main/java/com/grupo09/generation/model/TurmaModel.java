package com.grupo09.generation.model;

import java.util.List;

import com.grupo09.generation.dto.in.CreateTurma;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tab_turmas")
@Builder
public class TurmaModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "turma_id")
    private Long id;

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
    private String nome;

    @NotBlank(message = "Instrutor é obrigatório")
    @Size(min = 3, message = "Instrutor deve ter pelo menos 3 caracteres")
    private String instrutor;

    @OneToMany(mappedBy = "turma")
    private List<AlunoModel> alunos;

    public void addAluno(AlunoModel aluno) {
        alunos.add(aluno);
        aluno.setTurma(this);
    }

    public TurmaModel(String nome,String instrutor,List<AlunoModel> alunos){
        this.nome = nome;
        this.instrutor = instrutor;
        this.alunos = alunos;
    }

    public static TurmaModel toEntity(String nome,String instrutor,List<AlunoModel> alunos) {
        return new TurmaModel(nome, instrutor, alunos);
    }
}