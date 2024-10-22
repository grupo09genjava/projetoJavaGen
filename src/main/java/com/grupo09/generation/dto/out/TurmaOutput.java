package com.grupo09.generation.dto.out;

import com.grupo09.generation.model.AlunoModel;
import com.grupo09.generation.model.TurmaModel;

import java.util.ArrayList;
import java.util.List;

public record TurmaOutput(Long turmaId, String nome, String instrutor, List<AlunoOutput> alunos){
    public static TurmaOutput fromEntity(TurmaModel turmaModel){
        List<AlunoOutput> alunos = new ArrayList<>();
        for(AlunoModel alunoModel : turmaModel.getAlunos()){
            alunos.add(AlunoOutput.fromEntity(alunoModel));
        }
        return new TurmaOutput(turmaModel.getId(), turmaModel.getNome(),turmaModel.getInstrutor(), alunos);

    }
}
