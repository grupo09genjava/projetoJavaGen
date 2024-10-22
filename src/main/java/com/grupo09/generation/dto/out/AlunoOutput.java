package com.grupo09.generation.dto.out;

import com.grupo09.generation.model.AlunoModel;

public record AlunoOutput(Long alunoId, String name, String email, int idade, double notaPrimeiroModulo, double notaSegundoModulo, double media){
    public static AlunoOutput fromEntity(AlunoModel alunoModel){
        return new AlunoOutput(alunoModel.getId(), alunoModel.getNome(), alunoModel.getEmail(), alunoModel.getIdade(),
                            alunoModel.getNotaPrimeiroModulo(), alunoModel.getNotaSegundoModulo(),
                            alunoModel.getMedia());
    }
}
