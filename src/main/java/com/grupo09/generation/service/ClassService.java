package com.grupo09.generation.service;

import com.grupo09.generation.dto.in.RegisterStudent;
import com.grupo09.generation.dto.in.RegisterClass;
import com.grupo09.generation.dto.in.UpdateClass;
import com.grupo09.generation.dto.out.ClassOutput;
import com.grupo09.generation.exception.EmailAlreadyExistException;
import com.grupo09.generation.exception.NotFoundException;
import com.grupo09.generation.model.StudentModel;
import com.grupo09.generation.model.ClassModel;
import com.grupo09.generation.repository.StudentRepository;
import com.grupo09.generation.repository.ClassRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ClassService {
    private final ClassRepository classRepository;
    private final StudentRepository studentRepository;
    public List<ClassOutput> findAll() {
        return classRepository.findAll().stream()
                .map(ClassOutput::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClassOutput save(RegisterClass registerClass) {
        ClassModel classModel = ClassModel.builder()
                .name(registerClass.name())
                .instructor(registerClass.instructor())
                .students(new ArrayList<StudentModel>())
                .build();
        if (registerClass.students() != null) {
            for (RegisterStudent registerStudent : registerClass.students()) {
                StudentModel student = StudentModel.toEntity(registerStudent);
                Optional<StudentModel> foundStudent = studentRepository.findByEmail(student.getEmail());
                if (foundStudent.isPresent()) {
                    throw new EmailAlreadyExistException("Email already exists in the database");
                }
                classModel.addStudent(student);
            }
        }
        classRepository.save(classModel);
        return ClassOutput.fromEntity(classModel);
    }

    public ClassOutput findById(Long id) {
        ClassModel classModel = classRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Class not found"));
        return ClassOutput.fromEntity(classModel);
    }

    public void deleteById(Long id) {
        ClassModel classModel = classRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Class not found"));
        classRepository.deleteById(id);
    }

    public ClassOutput putById(Long id, UpdateClass updateClass) {
        ClassModel classModel = classRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Class not found"));
        classModel.setName(updateClass.name());
        classModel.setInstructor(updateClass.instructor());
        classRepository.save(classModel);
        return ClassOutput.fromEntity(classModel);
    }
}
