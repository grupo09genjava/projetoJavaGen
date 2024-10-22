package com.grupo09.generation.service;

import com.grupo09.generation.dto.in.CreateFuncionario;
import com.grupo09.generation.dto.in.UpdateFuncionario;
import com.grupo09.generation.dto.out.FuncionarioOutput;
import com.grupo09.generation.exception.EmailAlreadyExistException;
import com.grupo09.generation.exception.NotFoundException;
import org.springframework.stereotype.Service;
import com.grupo09.generation.repository.FuncionarioRepository;

import java.util.List;
import java.util.stream.Collectors;

import com.grupo09.generation.model.FuncionarioModel;

@Service
public class FuncionarioService {

    private final FuncionarioRepository repository;

    public FuncionarioService(FuncionarioRepository repository) {
        this.repository = repository;
    }

    // Método para criar um novo funcionário
    public FuncionarioOutput save(CreateFuncionario createFuncionario) {
        boolean validateEmail = repository.existsByEmail(createFuncionario.email());
        if(validateEmail){throw new EmailAlreadyExistException("Email já existe no banco de dados");}
        FuncionarioModel funcionarioModel = FuncionarioModel.builder()
                .nome(createFuncionario.nome())
                .email(createFuncionario.email())
                .senha(createFuncionario.senha())
                .cargo(createFuncionario.cargo())
                .build();

        repository.save(funcionarioModel);
        return FuncionarioOutput.fromEntity(funcionarioModel);
    }

    // Método para listar todos os funcionários
    public List<FuncionarioOutput> findAll() {
        return repository.findAll().stream()
                .map(FuncionarioOutput::fromEntity)
                .collect(Collectors.toList());
    }

    // Método para buscar um funcionário por ID
    public FuncionarioOutput findById(Long id) {
        FuncionarioModel funcionarioModel = repository.findById(id).orElseThrow(() -> new NotFoundException("Funcionário Não Encontrado"));
        return FuncionarioOutput.fromEntity(funcionarioModel);
    }

    // Método para atualizar os dados de um funcionário
    public FuncionarioOutput put(Long id, UpdateFuncionario updateFuncionario) {
        FuncionarioModel funcionarioModel = repository.findById(id).orElseThrow(() -> new NotFoundException("Funcionário Não Encontrado"));

        funcionarioModel.setNome(updateFuncionario.nome());
        funcionarioModel.setEmail(updateFuncionario.email());
        funcionarioModel.setSenha(updateFuncionario.senha());
        funcionarioModel.setCargo(updateFuncionario.cargo());
        repository.save(funcionarioModel);
        return FuncionarioOutput.fromEntity(funcionarioModel);
    }

    // Método para deletar um funcionário
    public void deleteById(Long id) {
        FuncionarioModel funcionarioModel = repository.findById(id).orElseThrow(() -> new NotFoundException("Funcionário não Encontrado"));
        repository.deleteById(id);
    }

}
