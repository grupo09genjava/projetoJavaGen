package com.grupo09.generation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.grupo09.generation.model.TurmaModel;

@Repository
public interface TurmaRepository extends JpaRepository<TurmaModel, Long> {
}
