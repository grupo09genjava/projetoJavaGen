package com.grupo09.generation.service;

import java.util.List;
import java.util.stream.Collectors;

import com.grupo09.generation.dto.in.UpdateStudent;
import com.grupo09.generation.dto.in.RegisterStudent;
import com.grupo09.generation.dto.out.StudentOutput;
import com.grupo09.generation.exception.EmailAlreadyExistException;
import com.grupo09.generation.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.grupo09.generation.model.StudentModel;
import com.grupo09.generation.repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class StudentService {
    private StudentRepository studentRepository;

    public List<StudentOutput> findAll() {
        return studentRepository.findAll().stream()
                .map(StudentOutput::fromEntity)
                .collect(Collectors.toList());
    }

    public StudentOutput save(RegisterStudent registerStudent) {
        if (studentRepository.findByEmail(registerStudent.email()).isPresent()) {
            throw new EmailAlreadyExistException("Email already registered in the database");
        }
        return StudentOutput.fromEntity(studentRepository.save(StudentModel.toEntity(registerStudent)));
    }

    public StudentOutput findById(Long id) {
        StudentModel studentModel = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found"));
        return StudentOutput.fromEntity(studentModel);
    }

    public StudentOutput putById(Long id, UpdateStudent updateStudent) {
        StudentModel studentModel = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found"));
        BeanUtils.copyProperties(updateStudent, studentModel);
        studentModel.updateAverage();
        return StudentOutput.fromEntity(studentRepository.save(studentModel));
    }

    public void deleteById(Long id) {
        StudentModel studentModel = studentRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Student not found"));
        studentRepository.deleteById(id);
    }
}
