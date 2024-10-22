package com.grupo09.generation.model;

import java.util.List;

import jakarta.persistence.*;
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
    @Column(nullable = false)
    private String nome;
    @Column(nullable = false)
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