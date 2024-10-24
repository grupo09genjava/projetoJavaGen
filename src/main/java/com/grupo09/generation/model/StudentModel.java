package com.grupo09.generation.model;

import com.grupo09.generation.dto.in.RegisterStudent;
import jakarta.persistence.*;
import lombok.*;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "tb_students")
public class StudentModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private Integer age;

    private Double firstModuleScore;
    private Double secondModuleScore;
    private Double average;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassModel tbClass;


    public StudentModel(String name,String email,Integer age) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.firstModuleScore = null;
        this.secondModuleScore = null;
        this.average = null;
    }


    public Optional<Double> getAverage() {
        return Optional.ofNullable(average);
    }

    public Optional<Double> getSecondModuleScore() {
        return Optional.ofNullable(secondModuleScore);
    }

    public Optional<Double> getFirstModuleScore() {
        return Optional.ofNullable(firstModuleScore);
    }

    public void updateAverage() {
        this.average = calculateAverage();
    }

    public static StudentModel toEntity(RegisterStudent registerStudent) {
        return new StudentModel(
                registerStudent.name(),
                registerStudent.email(),
                registerStudent.age()
        );
    }

    private Double calculateAverage() {
        if (firstModuleScore == null || secondModuleScore == null) {
            return null;
        }
        double result = (this.firstModuleScore + this.secondModuleScore) / 2;
        return Math.round(result * 10.0) / 10.0;
    }
}

