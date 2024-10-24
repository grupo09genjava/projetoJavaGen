package com.grupo09.generation.dto.in;

import com.grupo09.generation.model.StudentModel;
import com.grupo09.generation.model.ClassModel;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

public record RegisterClass(
        @NotNull(message = "Name is required.")
        @Size(min = 3, message = "Name must be at least 3 characters long.")
        String name,
        @NotNull(message = "Instructor is required.")
        @Size(min = 3, message = "Instructor must be at least 3 characters long.")
        String instructor,
        List<RegisterStudent> students){
    public static ClassModel toEntity(RegisterClass registerClass){
        List<StudentModel> students = new ArrayList<>();
        registerClass.students().forEach(student -> students.add(StudentModel.toEntity(student)));
        return ClassModel.toEntity(registerClass.name(),registerClass.instructor());
    }
}
