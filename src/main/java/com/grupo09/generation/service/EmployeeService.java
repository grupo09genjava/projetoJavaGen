package com.grupo09.generation.service;

import com.grupo09.generation.dto.in.RegisterEmployee;
import com.grupo09.generation.dto.in.UpdateEmployee;
import com.grupo09.generation.dto.out.EmployeeOutput;
import com.grupo09.generation.exception.EmailAlreadyExistException;
import com.grupo09.generation.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.grupo09.generation.repository.EmployeeRepository;

import java.util.List;
import java.util.stream.Collectors;

import com.grupo09.generation.model.EmployeeModel;

@Service
@AllArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeOutput save(RegisterEmployee registerEmployee) {
        boolean validateEmail = this.employeeRepository.existsByEmail(registerEmployee.email());
        if (validateEmail) {
            throw new EmailAlreadyExistException("Email already exists in the database");
        }
        EmployeeModel employeeModel = EmployeeModel.builder()
                .name(registerEmployee.name())
                .email(registerEmployee.email())
                .password(passwordEncoder.encode(registerEmployee.password()))
                .jobTitle(registerEmployee.jobTitle())
                .build();

        this.employeeRepository.save(employeeModel);
        return EmployeeOutput.fromEntity(employeeModel);
    }

    public List<EmployeeOutput> findAll() {
        return this.employeeRepository.findAll().stream()
                .map(EmployeeOutput::fromEntity)
                .collect(Collectors.toList());
    }

    public EmployeeOutput findById(Long id) {
        EmployeeModel employeeModel = this.employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee Not Found"));
        return EmployeeOutput.fromEntity(employeeModel);
    }

    public EmployeeOutput putById(Long id, UpdateEmployee updateEmployee) {
        EmployeeModel employeeModel = this.employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee Not Found"));
        BeanUtils.copyProperties(employeeModel, updateEmployee);
        this.employeeRepository.save(employeeModel);
        return EmployeeOutput.fromEntity(employeeModel);
    }

    public void deleteById(Long id) {
        EmployeeModel employeeModel = this.employeeRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Employee Not Found"));
        this.employeeRepository.deleteById(id);
    }
}
