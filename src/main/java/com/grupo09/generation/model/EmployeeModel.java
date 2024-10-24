package com.grupo09.generation.model;

import com.grupo09.generation.dto.in.LoginEmployee;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_employees")
@Builder
public class EmployeeModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String jobTitle;

    public boolean isLoginCorrect(LoginEmployee loginEmployee, PasswordEncoder passwordEncoder) {
        return passwordEncoder.matches(loginEmployee.password(), this.password);
    }
}
