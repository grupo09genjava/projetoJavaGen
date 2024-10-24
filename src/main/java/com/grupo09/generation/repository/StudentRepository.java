package com.grupo09.generation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo09.generation.model.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {
    Optional<StudentModel> findByEmail(String email);
}
