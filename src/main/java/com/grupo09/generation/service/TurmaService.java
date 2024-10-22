package com.grupo09.generation.service;

import com.grupo09.generation.dto.in.CreateAluno;
import com.grupo09.generation.dto.in.CreateTurma;
import com.grupo09.generation.dto.in.UpdateTurma;
import com.grupo09.generation.dto.out.TurmaOutput;
import com.grupo09.generation.exception.TurmaNotFoundException;
import com.grupo09.generation.model.AlunoModel;
import com.grupo09.generation.model.TurmaModel;
import com.grupo09.generation.repository.TurmaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TurmaService {
    private final TurmaRepository repository;

    public TurmaService(TurmaRepository repository) {
        this.repository = repository;
    }

    public List<TurmaOutput> findAll(){
        return repository.findAll().stream()
               .map(TurmaOutput::fromEntity)
               .collect(Collectors.toList());
    }



    public TurmaOutput save(CreateTurma createTurma){
        TurmaModel turmaModel = TurmaModel.builder()
                .nome(createTurma.nome())
                .instrutor(createTurma.instrutor())
                .alunos(new ArrayList<AlunoModel>())
                .build();
        if (createTurma.alunos() != null) {
            for (CreateAluno createAluno : createTurma.alunos()) {
                AlunoModel aluno = AlunoModel.toEntity(createAluno);
                turmaModel.addAluno(aluno);
            }
        }
        repository.save(turmaModel);
        return TurmaOutput.fromEntity(turmaModel);
    }

    public TurmaOutput findById(Long id){
        TurmaModel turmaModel = repository.findById(id).orElseThrow(() -> new TurmaNotFoundException("Turma não encontrada"));
        return TurmaOutput.fromEntity(turmaModel);
    }

    public void deleteById(Long id){
        TurmaModel turmaModel = repository.findById(id).orElseThrow(() -> new TurmaNotFoundException("Turma não encontrada"));
        repository.deleteById(id);
    }

    public TurmaOutput put(Long id, UpdateTurma updateTurma){
        TurmaModel turmaModel = repository.findById(id).orElseThrow(() -> new TurmaNotFoundException("Turma não encontrada"));
        turmaModel.setNome(updateTurma.nome());
        turmaModel.setInstrutor(updateTurma.instrutor());
        repository.save(turmaModel);
        return TurmaOutput.fromEntity(turmaModel);
    }
}
