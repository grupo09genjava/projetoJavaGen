package com.grupo09.generation.controller;

import com.grupo09.generation.dto.in.LoginEmployee;
import com.grupo09.generation.dto.in.RegisterEmployee;
import com.grupo09.generation.dto.out.EmployeeOutput;
import com.grupo09.generation.dto.out.LoginOutput;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
public class AuthControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;
    private RegisterEmployee registerEmployee;

    @BeforeEach
    void setUp() {
        registerEmployee = new RegisterEmployee(
                "Testonaldo",
                "testando@mail.com",
                "password123",
                "Developer"
        );
    }

    @Test
    @DisplayName("Should successfully register an employee")
    @DirtiesContext
    void shouldSignUpEmployee() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterEmployee> requestEntity = new HttpEntity<>(registerEmployee, headers);

        ResponseEntity<EmployeeOutput> response = testRestTemplate.exchange(
                "/api/v1/auth/signUp", HttpMethod.POST, requestEntity, EmployeeOutput.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).name()).isEqualTo("Testonaldo");
        assertThat(response.getBody().email()).isEqualTo("testando@mail.com");
        assertThat(response.getBody().jobTitle()).isEqualTo("Developer");
    }

    @Test
    @DisplayName("Should successfully authenticate an employee")
    @DirtiesContext
    void shouldLoginEmployee() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterEmployee> requestEntityRegister = new HttpEntity<>(registerEmployee, headers);

        testRestTemplate.exchange("/api/v1/auth/signUp", HttpMethod.POST, requestEntityRegister, EmployeeOutput.class);

        LoginEmployee loginEmployee = new LoginEmployee("testando@mail.com", "password123");
        HttpEntity<LoginEmployee> requestEntityLogin = new HttpEntity<>(loginEmployee, headers);
        ResponseEntity<LoginOutput> response = testRestTemplate.exchange(
                "/api/v1/auth/login", HttpMethod.POST, requestEntityLogin, LoginOutput.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getBody()).isNotNull();
        assertThat(Objects.requireNonNull(response.getBody()).accessToken()).isNotEmpty();
    }

    @Test
    @DisplayName("Should fail to authenticate with incorrect URL")
    void shouldFailLoginWithIncorrectUrl() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<LoginEmployee> requestEntityLogin = new HttpEntity<>(new LoginEmployee("testando@mail.com", "password123"), headers);
        ResponseEntity<LoginOutput> response = testRestTemplate.exchange(
                "/api/v1/auth/logIn",
                HttpMethod.POST,
                requestEntityLogin,
                LoginOutput.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}