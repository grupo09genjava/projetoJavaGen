package com.grupo09.generation.controller;

import com.grupo09.generation.dto.in.RegisterEmployee;
import com.grupo09.generation.dto.in.LoginEmployee;
import com.grupo09.generation.dto.out.EmployeeOutput;
import com.grupo09.generation.dto.out.LoginOutput;
import com.grupo09.generation.security.AuthenticationService;
import com.grupo09.generation.service.EmployeeService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController{
    private final AuthenticationService authenticationService;
    private final EmployeeService employeeService;

    public AuthController(AuthenticationService authenticationService,EmployeeService employeeService){
        this.authenticationService = authenticationService;
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginOutput> loginEmployee(@Valid @RequestBody LoginEmployee loginEmployee){

        return ResponseEntity.ok().body(authenticationService.authenticate(loginEmployee));
    }

    @PostMapping(path = "/signUp")
    public ResponseEntity<EmployeeOutput> registerEmployee(@Valid @RequestBody RegisterEmployee funcionario,UriComponentsBuilder uriComponentsBuilder){
        EmployeeOutput output = this.employeeService.save(funcionario);
        var uri = uriComponentsBuilder.path("/employees/{id}").buildAndExpand(output.employeeId()).toUri();
        return ResponseEntity.created(uri).body(output);
    }
}
