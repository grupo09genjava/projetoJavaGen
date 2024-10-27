package com.grupo09.generation.integration;

import com.grupo09.generation.dto.in.LoginEmployee;
import com.grupo09.generation.dto.in.RegisterEmployee;
import com.grupo09.generation.dto.out.EmployeeOutput;
import com.grupo09.generation.dto.out.LoginOutput;
import com.grupo09.generation.util.IntegrationTestFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.oauth2.server.resource.authentication.BearerTokenAuthentication;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class EmployeeControllerIT{
    @Autowired
    private TestRestTemplate testRestTemplate;
    private RegisterEmployee registerEmployee;
    @BeforeEach
    void setUp(){
        registerEmployee = new RegisterEmployee(
                "Testonaldo",
                "testando@mail.com",
                "password123A*",
                "Developer"
        );
    }

    @Test
    @DisplayName("Should successfully updated an employee")
    void shouldUpdateEmployee(){
        HttpEntity<RegisterEmployee> requestEntityRegister = IntegrationTestFactory.createRegisterEmployeeRequestEntity(registerEmployee);
        ResponseEntity<EmployeeOutput> responseRegister = testRestTemplate.exchange(
                "/api/v1/auth/signUp",HttpMethod.POST,requestEntityRegister, EmployeeOutput.class);

        LoginEmployee loginEmployee = new LoginEmployee("testando@mail.com","password123A*");
        HttpEntity<LoginEmployee> requestEntityLogin = IntegrationTestFactory.createLoginEmployeeRequestEntity(loginEmployee);
        ResponseEntity<LoginOutput> responseLogin = testRestTemplate.exchange(
                "/api/v1/auth/login",HttpMethod.POST,requestEntityLogin, LoginOutput.class);

        EmployeeOutput employeeOutput = new EmployeeOutput(Objects.requireNonNull(responseRegister.getBody()).employeeId(),"Joseph Michael","josephteste@email.com","Instructor");
        HttpEntity<EmployeeOutput> requestEntityUpdate = IntegrationTestFactory.createEmployeeUpdateRequestEntity(employeeOutput,Objects.requireNonNull(
                responseLogin.getBody()).accessToken());
        ResponseEntity<EmployeeOutput> responseUpdate = testRestTemplate.exchange(
                "/api/v1/employees/" + Objects.requireNonNull(responseRegister.getBody()).employeeId(), HttpMethod.PUT, requestEntityUpdate, EmployeeOutput.class);

        assertThat(responseUpdate.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseUpdate.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(responseRegister.getBody()).employeeId()).isEqualTo(
                Objects.requireNonNull(responseUpdate.getBody()).employeeId());
        assertThat(Objects.requireNonNull(responseRegister.getBody()).name()).isEqualTo(
                Objects.requireNonNull(responseUpdate.getBody()).name());
        assertThat(Objects.requireNonNull(responseRegister.getBody()).email()).isEqualTo(
                Objects.requireNonNull(responseUpdate.getBody()).email());
        assertThat(Objects.requireNonNull(responseRegister.getBody()).jobTitle()).isEqualTo(
                Objects.requireNonNull(responseUpdate.getBody()).jobTitle());
    }

    @Test
    @DisplayName("Should successfully retrieve an employee")
    void shouldRetrieveEmployeeById(){
        HttpEntity<RegisterEmployee> requestEntityRegister = IntegrationTestFactory.createRegisterEmployeeRequestEntity(registerEmployee);
        ResponseEntity<EmployeeOutput> responseRegister = testRestTemplate.exchange(
                "/api/v1/auth/signUp",HttpMethod.POST,requestEntityRegister, EmployeeOutput.class);

        LoginEmployee loginEmployee = new LoginEmployee("testando@mail.com","password123A*");
        HttpEntity<LoginEmployee> requestEntityLogin = IntegrationTestFactory.createLoginEmployeeRequestEntity(loginEmployee);
        ResponseEntity<LoginOutput> responseLogin = testRestTemplate.exchange(
                "/api/v1/auth/login",HttpMethod.POST,requestEntityLogin, LoginOutput.class);

        HttpEntity<Void> requestEntityRetrieve = IntegrationTestFactory.createNoContentRequestEntity(Objects.requireNonNull(
                responseLogin.getBody()).accessToken());
        ResponseEntity<EmployeeOutput> responseRetrieve = testRestTemplate.exchange(
                "/api/v1/employees/" + Objects.requireNonNull(responseRegister.getBody()).employeeId(), HttpMethod.GET, requestEntityRetrieve, EmployeeOutput.class);

        assertThat(responseRetrieve.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseRetrieve.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(responseRegister.getBody()).employeeId()).isEqualTo(
                Objects.requireNonNull(responseRetrieve.getBody()).employeeId());
        assertThat(Objects.requireNonNull(responseRegister.getBody()).name()).isEqualTo(
                Objects.requireNonNull(responseRetrieve.getBody()).name());
        assertThat(Objects.requireNonNull(responseRegister.getBody()).email()).isEqualTo(
                Objects.requireNonNull(responseRetrieve.getBody()).email());
        assertThat(Objects.requireNonNull(responseRegister.getBody()).jobTitle()).isEqualTo(
                Objects.requireNonNull(responseRetrieve.getBody()).jobTitle());
    }

    @Test
    @DisplayName("Should successfully delete an employee")
    void shouldDeleteEmployee(){
        HttpEntity<RegisterEmployee> requestEntityRegister = IntegrationTestFactory.createRegisterEmployeeRequestEntity(registerEmployee);
        ResponseEntity<EmployeeOutput> responseRegister = testRestTemplate.exchange(
                "/api/v1/auth/signUp",HttpMethod.POST,requestEntityRegister, EmployeeOutput.class);

        LoginEmployee loginEmployee = new LoginEmployee("testando@mail.com","password123A*");
        HttpEntity<LoginEmployee> requestEntityLogin = IntegrationTestFactory.createLoginEmployeeRequestEntity(loginEmployee);
        ResponseEntity<LoginOutput> responseLogin = testRestTemplate.exchange(
                "/api/v1/auth/login",HttpMethod.POST,requestEntityLogin, LoginOutput.class);

        HttpEntity<Void> requestEntityNoContent = IntegrationTestFactory.createNoContentRequestEntity(Objects.requireNonNull(
                responseLogin.getBody()).accessToken());
                ResponseEntity<Void> responseDelete = testRestTemplate.exchange(
                "/api/v1/employees/" + Objects.requireNonNull(responseRegister.getBody()).employeeId(), HttpMethod.DELETE, requestEntityNoContent, Void.class);
        assertThat(responseDelete.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(responseDelete.getBody()).isNull();
        ResponseEntity<EmployeeOutput> responseRetrieve = testRestTemplate.exchange(
                "/api/v1/employees/" + Objects.requireNonNull(responseRegister.getBody()).employeeId(), HttpMethod.GET, requestEntityNoContent, EmployeeOutput.class);
        assertThat(responseRetrieve.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}