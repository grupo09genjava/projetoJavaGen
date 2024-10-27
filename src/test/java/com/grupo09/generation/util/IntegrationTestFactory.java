package com.grupo09.generation.util;

import com.grupo09.generation.dto.in.LoginEmployee;
import com.grupo09.generation.dto.in.RegisterEmployee;
import com.grupo09.generation.dto.out.EmployeeOutput;
import com.grupo09.generation.dto.out.LoginOutput;
import org.springframework.http.*;

import java.util.Objects;

public class IntegrationTestFactory{

    public static HttpEntity<RegisterEmployee> createRegisterEmployeeRequestEntity(RegisterEmployee employee) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(employee, headers);
    }

    public static HttpEntity<LoginEmployee> createLoginEmployeeRequestEntity(LoginEmployee login) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new HttpEntity<>(login, headers);
    }

    public static HttpEntity<EmployeeOutput> createEmployeeUpdateRequestEntity(EmployeeOutput employeeOutput,String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);
        return new HttpEntity<>(employeeOutput, headers);
    }

    public static HttpEntity<Void> createNoContentRequestEntity(String accessToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);
        return new HttpEntity<>(headers);
    }
}
