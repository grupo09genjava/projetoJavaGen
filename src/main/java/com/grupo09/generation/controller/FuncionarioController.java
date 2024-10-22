package com.grupo09.generation.controller;

import com.grupo09.generation.dto.in.CreateFuncionario;
import com.grupo09.generation.dto.in.UpdateFuncionario;
import com.grupo09.generation.dto.out.FuncionarioOutput;
import com.grupo09.generation.service.FuncionarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @Operation(description = "Busca o Funcionário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna o Funcionário"),
            @ApiResponse(responseCode = "400", description = "Funcionário Não Encontrada")
    })
    @GetMapping(path = "/api/funcionario/{id}")
    public ResponseEntity<FuncionarioOutput> getFuncionario(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(service.findById(id));

    }
    @Operation(description = "Busca Todos os Funcionários cadastrados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retorna os Funcionários"),
    })
    @GetMapping(path = "/api/funcionarios")
    public ResponseEntity<Iterable<FuncionarioOutput>> listFuncionarios(){
        return ResponseEntity.ok().body(service.findAll());
    }

    @Operation(description = "Cadastra o Funcionário")
    @PostMapping(path = "/api/funcionario/salvar")
    public ResponseEntity<FuncionarioOutput> postFuncionario(@RequestBody CreateFuncionario funcionario, UriComponentsBuilder uriComponentsBuilder){
        FuncionarioOutput output = service.save(funcionario);
        var uri = uriComponentsBuilder.path("/api/funcionario/{id}").buildAndExpand(output.funcionarioId()).toUri();
        return ResponseEntity.created(uri).body(output);
    }

    @PutMapping(path = "/api/funcionario/{id}")
    public ResponseEntity<FuncionarioOutput> updateFuncionario(@PathVariable("id") Long id, @RequestBody UpdateFuncionario funcionario) {
        return ResponseEntity.ok().body(service.put(id, funcionario));
    }

    @Operation(description = "Deleta um Funcionário")
    @DeleteMapping(path = "/api/funcionario/{id}")
    public ResponseEntity<Void> deleteFuncionario(@PathVariable("id") Long id){
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
