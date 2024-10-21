package com.grupo09.generation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo09.generation.repository.FuncionarioRepository;
import java.util.List;
import java.util.Optional;

import com.grupo09.generation.model.FuncionarioModel;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    // Método para criar um novo funcionário
    public FuncionarioModel criarFuncionario(FuncionarioModel funcionario) throws Exception {
        // Verificar se o email já existe
        if (funcionarioRepository.existsByEmail(funcionario.getEmail())) {
            throw new Exception("Email já cadastrado!");
        }
        // Salvar o novo funcionário no banco de dados
        return funcionarioRepository.save(funcionario);
    }

    // Método para listar todos os funcionários
    public List<FuncionarioModel> listarFuncionarios() {
        return funcionarioRepository.findAll();
    }

    // Método para buscar um funcionário por ID
    public FuncionarioModel buscarFuncionarioPorId(Long id) throws Exception {
        Optional<FuncionarioModel> funcionario = funcionarioRepository.findById(id);
        if (funcionario.isPresent()) {
            return funcionario.get();
        } else {
            throw new Exception("Funcionário não encontrado!");
        }
    }

    // Método para atualizar os dados de um funcionário
    public FuncionarioModel atualizarFuncionario(Long id, FuncionarioModel funcionarioAtualizado) throws Exception {
        FuncionarioModel funcionarioExistente = buscarFuncionarioPorId(id);
        // Atualizando os dados do funcionário
        funcionarioExistente.setNome(funcionarioAtualizado.getNome());
        funcionarioExistente.setEmail(funcionarioAtualizado.getEmail());
        funcionarioExistente.setCargo(funcionarioAtualizado.getCargo());
        return funcionarioRepository.save(funcionarioExistente);
    }

    // Método para deletar um funcionário
    public void deletarFuncionario(Long id) throws Exception {
        FuncionarioModel funcionario = buscarFuncionarioPorId(id);
        funcionarioRepository.delete(funcionario);
    }

}
