package com.grupo09.generation.dto.out;

import com.grupo09.generation.model.StudentModel;
import com.grupo09.generation.model.ClassModel;

import java.util.ArrayList;
import java.util.List;

public record ClassOutput(
        Long classId,
        String name,
        String instructor,
        List<StudentOutput> students
) {
    public static ClassOutput fromEntity(ClassModel classModel) {
        List<StudentOutput> students = new ArrayList<>();
        for (StudentModel studentModel : classModel.getStudents()) {
            students.add(StudentOutput.fromEntity(studentModel));
        }
        return new ClassOutput(
                classModel.getId(),
                classModel.getName(),
                classModel.getInstructor(),
                students
        );
    }
}