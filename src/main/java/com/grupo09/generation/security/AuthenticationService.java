package com.grupo09.generation.security;

import com.grupo09.generation.dto.in.LoginEmployee;
import com.grupo09.generation.dto.out.LoginOutput;
import com.grupo09.generation.exception.NotFoundException;
import com.grupo09.generation.exception.UnauthorizedException;
import com.grupo09.generation.model.EmployeeModel;
import com.grupo09.generation.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService{
    private EmployeeRepository employeeRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public AuthenticationService(EmployeeRepository employeeRepository,JwtService jwtService,PasswordEncoder passwordEncoder){
        this.employeeRepository = employeeRepository;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginOutput authenticate(LoginEmployee loginEmployee) {
        EmployeeModel funcionario = this.employeeRepository.findByEmail(
                loginEmployee.email()).orElseThrow(() -> new NotFoundException("Funcionario nao encontrado no banco de dados"));
        if(!funcionario.isLoginCorrect(loginEmployee,passwordEncoder)){
            throw new UnauthorizedException("Insira as credenciais validas");
        }
        return jwtService.generateToken(funcionario);
    }
}
