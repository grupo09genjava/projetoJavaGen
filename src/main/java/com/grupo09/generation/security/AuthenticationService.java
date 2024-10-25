package com.grupo09.generation.security;

import com.grupo09.generation.dto.in.LoginEmployee;
import com.grupo09.generation.dto.out.LoginOutput;
import com.grupo09.generation.exception.UnauthorizedException;
import com.grupo09.generation.model.EmployeeModel;
import com.grupo09.generation.repository.EmployeeRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {
    private final EmployeeRepository employeeRepository;
    private final JwtGenerate jwtGenerate;
    private final PasswordEncoder passwordEncoder;

    public AuthenticationService(EmployeeRepository employeeRepository,JwtGenerate jwtGenerate,PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.jwtGenerate = jwtGenerate;
        this.passwordEncoder = passwordEncoder;
    }

    public LoginOutput authenticate(LoginEmployee loginEmployee) {
        Optional<EmployeeModel> employee = this.employeeRepository.findByEmail(loginEmployee.email());
        if (employee.isEmpty() || !employee.get().isLoginCorrect(loginEmployee,passwordEncoder)) {
            throw new UnauthorizedException("Please enter valid credentials");
        }
        return jwtGenerate.token(employee.get());
    }
}

