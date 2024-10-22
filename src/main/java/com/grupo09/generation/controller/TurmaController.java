package com.grupo09.generation.controller;

import com.grupo09.generation.dto.in.CreateTurma;
import com.grupo09.generation.dto.in.UpdateTurma;
import com.grupo09.generation.dto.out.TurmaOutput;
import com.grupo09.generation.model.TurmaModel;
import com.grupo09.generation.repository.TurmaRepository;
import com.grupo09.generation.service.TurmaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class TurmaController {

    @Autowired
    private TurmaService service;

    @Operation(description = "Busca a Turma pelo ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retorna a Turma"),
        @ApiResponse(responseCode = "400", description = "Turma Não Encontrada")
    })
    @GetMapping(path = "/api/turma/{id}")
    public ResponseEntity<TurmaOutput> getTurma (@PathVariable("id") Long id){
        return ResponseEntity.ok().body(service.findById(id));

    }
    @Operation(description = "Busca Todas as Turmas Cadastradas")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna a Turma"),
    })
    @GetMapping(path = "/api/turmas")
    public ResponseEntity<Iterable<TurmaOutput>> listTurmas(){
        return ResponseEntity.ok().body(service.findAll());
    }
    @Operation(description = "Cadastra a Turma")
    @PostMapping(path = "/api/turma/salvar")
    public ResponseEntity<TurmaOutput> postTurma(@RequestBody CreateTurma turma, UriComponentsBuilder uriComponentsBuilder){
        TurmaOutput output = service.save(turma);
        var uri = uriComponentsBuilder.path("/api/turma/{id}").buildAndExpand(output.turmaId()).toUri();
        return ResponseEntity.created(uri).body(output);
    }

    @PutMapping
    public ResponseEntity<TurmaOutput> updateTurma(@PathVariable("id") Long id, @RequestBody UpdateTurma turma) {
        return ResponseEntity.ok().body(service.put(id, turma));
    }

    @Operation(description = "Deleta uma Turma")
    @DeleteMapping(path = "/api/turma/{id}")
    public ResponseEntity<Void> deleteTurma(@PathVariable("id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

