package com.grupo09.generation.controller;

import com.grupo09.generation.dto.in.CreateAluno;
import com.grupo09.generation.dto.in.UpdateAluno;
import com.grupo09.generation.dto.out.AlunoOutput;
import com.grupo09.generation.model.AlunoModel;
import com.grupo09.generation.service.AlunoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1")
public class AlunoController {
    private final AlunoService service;

    public AlunoController(AlunoService service){
        this.service = service;
    }

    @PostMapping(path = "/aluno/cadastrar")
    public ResponseEntity<AlunoOutput> cadastrarAluno(@Valid @RequestBody CreateAluno createAluno,UriComponentsBuilder uriComponentsBuilder){
        var output = service.save(createAluno);
        var uri = uriComponentsBuilder.path("/turma/{id}").buildAndExpand(output.alunoId()).toUri();
        return ResponseEntity.created(uri).body(output);
    }

    @GetMapping(path = "/aluno")
    public ResponseEntity<Iterable<AlunoOutput>> findAll(){
        return ResponseEntity.ok(service.findAll());
    }
    @GetMapping(path = "/aluno/{id}")
    public ResponseEntity<AlunoOutput> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(service.findById(id));
    }

    @PutMapping(path = "/aluno/{id}")
    public ResponseEntity<AlunoOutput> put(@PathVariable("id") Long id, @Valid @RequestBody UpdateAluno updateAluno){
        return ResponseEntity.ok().body(service.put(id, updateAluno));
    }

    @DeleteMapping(path = "/aluno/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
