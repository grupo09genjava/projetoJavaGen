package com.grupo09.generation.service;

import java.util.List;
import java.util.stream.Collectors;

import com.grupo09.generation.dto.in.UpdateAluno;
import com.grupo09.generation.dto.in.CreateAluno;
import com.grupo09.generation.dto.out.AlunoOutput;
import com.grupo09.generation.exception.EmailAlreadyExistException;
import com.grupo09.generation.exception.NotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.grupo09.generation.model.AlunoModel;
import com.grupo09.generation.repository.AlunoRepository;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository alunoRepository;
    
    public List<AlunoOutput> findAll() {
        return alunoRepository.findAll().stream()
                .map(AlunoOutput::fromEntity)
                .collect(Collectors.toList());
    }

    public AlunoOutput save(CreateAluno createAluno) {
        if (alunoRepository.findByEmail(createAluno.email()).isPresent()) {
            throw new EmailAlreadyExistException("Email já cadastrado no banco de dados");
        }
        return AlunoOutput.fromEntity(alunoRepository.save(AlunoModel.toEntity(createAluno)));
    }

    public AlunoOutput findById(Long id) {
         AlunoModel alunoModel = alunoRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
         return AlunoOutput.fromEntity(alunoModel);
    }

    public AlunoOutput put(Long id, UpdateAluno updateAluno){
        AlunoModel alunoModel = alunoRepository.findById(id).orElseThrow(() -> new NotFoundException("Aluno não encontrado"));
        BeanUtils.copyProperties(updateAluno, alunoModel);
        alunoModel.atualizarMedia();
        return AlunoOutput.fromEntity(alunoRepository.save(alunoModel));
    }

    public void deleteById(Long id) {
        alunoRepository.deleteById(id);
    }
}