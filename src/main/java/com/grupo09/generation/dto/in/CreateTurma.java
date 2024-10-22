package com.grupo09.generation.dto.in;

import com.grupo09.generation.model.AlunoModel;
import com.grupo09.generation.model.TurmaModel;

import java.util.ArrayList;
import java.util.List;

public record CreateTurma(String nome, String instrutor, List<CreateAluno> alunos){
    public static TurmaModel toEntity(CreateTurma createTurma){
        List<AlunoModel> alunos = new ArrayList<>();
        createTurma.alunos().forEach(aluno -> alunos.add(AlunoModel.toEntity(aluno)));
        return TurmaModel.toEntity(createTurma.nome(), createTurma.instrutor(), alunos);
    }
}
