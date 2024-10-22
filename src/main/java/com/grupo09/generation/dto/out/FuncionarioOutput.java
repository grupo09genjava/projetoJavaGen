package com.grupo09.generation.dto.out;

import com.grupo09.generation.model.AlunoModel;
import com.grupo09.generation.model.FuncionarioModel;

import java.util.ArrayList;

public record FuncionarioOutput(Long funcionarioId, String nome, String email, String senha, String cargo) {

    public static FuncionarioOutput fromEntity(FuncionarioModel funcionarioModel){
        return new FuncionarioOutput(funcionarioModel.getId(), funcionarioModel.getNome(), funcionarioModel.getEmail(), funcionarioModel.getSenha(), funcionarioModel.getCargo());

    }
}
