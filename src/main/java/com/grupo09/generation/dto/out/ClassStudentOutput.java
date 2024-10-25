package com.grupo09.generation.dto.out;

import com.grupo09.generation.model.StudentModel;

import java.util.Optional;

public record ClassStudentOutput(
        Long studentId,
        String name,
        String email,
        int age,
        Optional<Double> firstModuleScore,
        Optional<Double> secondModuleScore,
        Optional<Double> average
) {
    public static ClassStudentOutput fromEntity(StudentModel studentModel) {
        return new ClassStudentOutput(
                studentModel.getId(),
                studentModel.getName(),
                studentModel.getEmail(),
                studentModel.getAge(),
                studentModel.getFirstModuleScore(),
                studentModel.getSecondModuleScore(),
                studentModel.getAverage()
        );
    }
}

