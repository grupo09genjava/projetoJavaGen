package com.grupo09.generation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import com.grupo09.generation.model.AlunoModel;
import com.grupo09.generation.repository.AlunoRepository;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    
    public List<AlunoModel> getAll() {
        return alunoRepository.findAll();
    }

    public AlunoModel salvar(AlunoModel aluno) {
        if (alunoRepository.findByEmail(aluno.getEmail()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Email já cadastrado.");
        }
        return alunoRepository.save(aluno);
    }

    public AlunoModel buscarPorId(Long id) {
        return alunoRepository.findById(id)
            .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
    }

    public void deletar(Long id) {
        alunoRepository.deleteById(id);
    }
}