package com.grupo09.generation.service;

import com.grupo09.generation.dto.in.CreateTurma;
import com.grupo09.generation.dto.in.UpdateTurma;
import com.grupo09.generation.dto.out.TurmaOutput;
import com.grupo09.generation.exception.NotFoundException;
import com.grupo09.generation.model.TurmaModel;
import com.grupo09.generation.repository.TurmaRepository;
import com.grupo09.generation.util.TestModelFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(SpringExtension.class)
class TurmaServiceTest{
    @InjectMocks
    private TurmaService turmaService;

    @Mock
    private TurmaRepository turmaRepository;

    @BeforeEach()
    void setUp()  {
        BDDMockito.when(turmaRepository.findAll()).thenReturn(List.of(TestModelFactory.createTurmaWithDefaultAlunos()));
        BDDMockito.when(turmaRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(TestModelFactory.createTurmaWithDefaultAlunos()));
        BDDMockito.when(turmaRepository.save(ArgumentMatchers.any(TurmaModel.class))).thenReturn(TestModelFactory.createTurmaWithDefaultAlunos());
        BDDMockito.doNothing().when(turmaRepository).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    void findAll_shouldReturnListOfTurmaOutput_whenCalled(){
        List<TurmaOutput> turmas = turmaService.findAll();
        assertEquals(1, turmas.size());
        assertEquals(TurmaOutput.fromEntity(TestModelFactory.createTurmaWithDefaultAlunos()), turmas.get(0));
    }

    @Test
    void save_shouldReturnTurmaOutput_whenValidCreateTurmaProvided(){
        CreateTurma createTurma = new CreateTurma("Turma do Teste", "Joseph", List.of());
        TurmaOutput output = turmaService.save(createTurma);
        assertEquals(createTurma.nome(), output.nome());
        assertEquals(createTurma.instrutor(), output.instrutor());
        assertEquals(List.of(), output.alunos());
    }

    @Test
    void findById_shouldReturnTurma_whenGivenValidId(){
        TurmaOutput turma = turmaService.findById(1L);
        assertEquals(TurmaOutput.fromEntity(TestModelFactory.createTurmaWithDefaultAlunos()), turma);
        assertEquals(TurmaOutput.fromEntity(TestModelFactory.createTurmaWithDefaultAlunos()).alunos(), turma.alunos());
        assertEquals(TurmaOutput.fromEntity(TestModelFactory.createTurmaWithDefaultAlunos()).turmaId(), turma.turmaId());
        assertEquals(TurmaOutput.fromEntity(TestModelFactory.createTurmaWithDefaultAlunos()).nome(), turma.nome());
    }

    @Test
    void findById_whenNotFound_shouldThrowException() {
        BDDMockito.when(turmaRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class,() -> turmaService.findById(2L));
    }

    @Test
    void deleteById_shouldRemoveTurma_whenIdExists(){
        Long deleteId = 1L;
        turmaService.deleteById(deleteId);
        verify(turmaRepository).deleteById(deleteId);
    }

    @Test
    void deleteById_whenExists_shouldCallDelete() {
        Long deleteId = 1L;
        BDDMockito.when(turmaRepository.findById(deleteId)).thenReturn(Optional.of(TestModelFactory.createTurmaWithDefaultAlunos()));

        turmaService.deleteById(deleteId);

        verify(turmaRepository).deleteById(deleteId);
    }

    @Test
    void put_shouldUpdateTurma_whenValidDataProvided(){
        UpdateTurma updateTurma = new UpdateTurma("Turma do Teste Alterada","Joseph Alterado");
        TurmaOutput output = turmaService.put(1L, updateTurma);
        assertEquals(updateTurma.nome(), output.nome());
        assertEquals(updateTurma.instrutor(), output.instrutor());
        assertEquals(TurmaOutput.fromEntity(TestModelFactory.createTurmaWithDefaultAlunos()).alunos(), output.alunos());
    }
}