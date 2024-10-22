package com.grupo09.generation.dto.out;

import com.grupo09.generation.model.AlunoModel;

import java.util.Optional;

public record AlunoOutput(Long alunoId,String nome,String email,int idade,Optional<Double> notaPrimeiroModulo,Optional<Double> notaSegundoModulo,Optional<Double> media){
    public static AlunoOutput fromEntity(AlunoModel alunoModel){
        return new AlunoOutput(alunoModel.getId(), alunoModel.getNome(), alunoModel.getEmail(), alunoModel.getIdade(),
                            alunoModel.getNotaPrimeiroModulo(), alunoModel.getNotaSegundoModulo(),
                            alunoModel.getMedia());
    }
}
