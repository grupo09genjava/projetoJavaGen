package com.grupo09.generation.dto.in;

import com.grupo09.generation.model.AlunoModel;
import com.grupo09.generation.model.TurmaModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public record CreateTurma(
        @NotNull(message = "Nome é obrigatório")
        @Size(min = 3, message = "Nome deve ter pelo menos 3 caracteres")
        String nome,
        @NotNull(message = "Instrutor é obrigatório")
        @Size(min = 3, message = "Instrutor deve ter pelo menos 3 caracteres")
        String instrutor,
        List<CreateAluno> alunos){
    public static TurmaModel toEntity(CreateTurma createTurma){
        List<AlunoModel> alunos = new ArrayList<>();
        createTurma.alunos().forEach(aluno -> alunos.add(AlunoModel.toEntity(aluno)));
        return TurmaModel.toEntity(createTurma.nome(),createTurma.instrutor(),alunos);
    }
}
