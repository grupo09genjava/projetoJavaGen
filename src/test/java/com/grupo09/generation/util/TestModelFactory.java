package com.grupo09.generation.util;
import com.grupo09.generation.model.StudentModel;
import com.grupo09.generation.model.ClassModel;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
public class TestModelFactory {
    public static ClassModel createClass(Long id, String name, String instructor, List<StudentModel> students) {
        return ClassModel.builder()
                .id(id)
                .name(name)
                .instructor(instructor)
                .students(students)
                .build();
    }

    public static StudentModel createDefaultStudent(Long id, String name, ClassModel classModel) {
        var email = name + "@mail.com";
        return createStudent(id, name, email, 18, 7.0, 7.5, 7.25, classModel);
    }

    public static StudentModel createStudent(Long id, String name, String email, int age,
                                             double firstModuleGrade, double secondModuleGrade,
                                             double average, ClassModel classModel) {
        return StudentModel.builder()
                .id(id)
                .name(name)
                .email(email)
                .age(age)
                .firstModuleScore(firstModuleGrade)
                .secondModuleScore(secondModuleGrade)
                .average(average)
                .tbClass(classModel)
                .build();
    }

    public static ClassModel createClassWithDefaultStudents() {
        ClassModel classModel = createClass(1L, "Class 1", "Joselito", Collections.emptyList());
        StudentModel student1 = createDefaultStudent(1L, "João", classModel);
        StudentModel student2 = createDefaultStudent(2L, "Maria", classModel);
        classModel.setStudents(Arrays.asList(student1, student2));
        return classModel;
    }

    public static ClassModel createEmptyClass() {
        return createClass(2L, "Class 2", "Cloudio", Collections.emptyList());
    }
}
