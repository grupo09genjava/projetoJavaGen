package com.grupo09.generation.service;

import com.grupo09.generation.dto.in.CreateAluno;
import com.grupo09.generation.dto.in.CreateTurma;
import com.grupo09.generation.dto.in.UpdateTurma;
import com.grupo09.generation.dto.out.TurmaOutput;
import com.grupo09.generation.exception.AlunoAlreadyExistException;
import com.grupo09.generation.exception.TurmaNotFoundException;
import com.grupo09.generation.model.AlunoModel;
import com.grupo09.generation.model.TurmaModel;
import com.grupo09.generation.repository.AlunoRepository;
import com.grupo09.generation.repository.TurmaRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TurmaService {
    private final TurmaRepository turmaRepository;
    private final AlunoRepository alunoRepository;

    public TurmaService(TurmaRepository turmaRepository,AlunoRepository alunoRepository){
        this.turmaRepository = turmaRepository;
        this.alunoRepository = alunoRepository;
    }

    public List<TurmaOutput> findAll(){
        return turmaRepository.findAll().stream()
               .map(TurmaOutput::fromEntity)
               .collect(Collectors.toList());
    }

    @Transactional
    public TurmaOutput save(CreateTurma createTurma){
        TurmaModel turmaModel = TurmaModel.builder()
                .nome(createTurma.nome())
                .instrutor(createTurma.instrutor())
                .alunos(new ArrayList<AlunoModel>())
                .build();
        if (createTurma.alunos() != null) {
            for (CreateAluno createAluno : createTurma.alunos()) {
                AlunoModel aluno = AlunoModel.toEntity(createAluno);
                Optional<AlunoModel> foundAluno = alunoRepository.findByEmail(aluno.getEmail());
                if (foundAluno.isPresent()) {
                    throw new AlunoAlreadyExistException("Email já existente no banco de dados");
                }
                AlunoModel savedAluno = alunoRepository.save(aluno);
                turmaModel.addAluno(savedAluno);
            }
        }
        turmaRepository.save(turmaModel);
        return TurmaOutput.fromEntity(turmaModel);
    }

    public TurmaOutput findById(Long id){
        TurmaModel turmaModel = turmaRepository.findById(id).orElseThrow(() -> new TurmaNotFoundException("Turma não encontrada"));
        return TurmaOutput.fromEntity(turmaModel);
    }

    public void deleteById(Long id){
        TurmaModel turmaModel = turmaRepository.findById(id).orElseThrow(() -> new TurmaNotFoundException("Turma não encontrada"));
        turmaRepository.deleteById(id);
    }

    public TurmaOutput put(Long id, UpdateTurma updateTurma){
        TurmaModel turmaModel = turmaRepository.findById(id).orElseThrow(() -> new TurmaNotFoundException("Turma não encontrada"));
        turmaModel.setNome(updateTurma.nome());
        turmaModel.setInstrutor(updateTurma.instrutor());
        turmaRepository.save(turmaModel);
        return TurmaOutput.fromEntity(turmaModel);
    }
}
