package com.grupo09.generation.service;

import com.grupo09.generation.dto.in.RegisterClass;
import com.grupo09.generation.dto.in.UpdateClass;
import com.grupo09.generation.dto.out.ClassOutput;
import com.grupo09.generation.exception.NotFoundException;
import com.grupo09.generation.model.ClassModel;
import com.grupo09.generation.repository.ClassRepository;
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
class ClassServiceTest {
    @InjectMocks
    private ClassService classService;

    @Mock
    private ClassRepository classRepository;

    private ClassModel defaultClass;

    @BeforeEach
    void setUp() {
        defaultClass = TestModelFactory.createClassWithDefaultStudents();

        BDDMockito.when(classRepository.findAll()).thenReturn(List.of(defaultClass));
        BDDMockito.when(classRepository.findById(ArgumentMatchers.anyLong())).thenReturn(Optional.of(defaultClass));
        BDDMockito.when(classRepository.save(ArgumentMatchers.any(ClassModel.class))).thenReturn(defaultClass);
        BDDMockito.doNothing().when(classRepository).deleteById(ArgumentMatchers.anyLong());
    }

    @Test
    void findAll_shouldReturnListOfClassOutput_whenCalled() {
        List<ClassOutput> classes = classService.findAll();
        assertEquals(1, classes.size());
        assertEquals(ClassOutput.fromEntity(defaultClass), classes.get(0));
    }

    @Test
    void save_shouldReturnClassOutput_whenValidRegisterClassProvided() {
        RegisterClass registerClass = new RegisterClass("Test Class", "Joseph", List.of());
        ClassOutput output = classService.save(registerClass);
        assertEquals(registerClass.name(), output.name());
        assertEquals(registerClass.instructor(), output.instructor());
        assertEquals(List.of(), output.students());
    }

    @Test
    void findById_shouldReturnClass_whenGivenValidId() {
        ClassOutput classOutput = classService.findById(1L);
        assertEquals(ClassOutput.fromEntity(defaultClass), classOutput);
        assertEquals(defaultClass.getStudents(), classOutput.students());
        assertEquals(defaultClass.getId(), classOutput.classId());
        assertEquals(defaultClass.getName(), classOutput.name());
    }

    @Test
    void findById_whenNotFound_shouldThrowException() {
        BDDMockito.when(classRepository.findById(2L)).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> classService.findById(2L));
    }

    @Test
    void deleteById_shouldRemoveClass_whenIdExists() {
        Long deleteId = 1L;
        classService.deleteById(deleteId);
        verify(classRepository).deleteById(deleteId);
    }

    @Test
    void put_shouldUpdateClass_whenValidDataProvided() {
        UpdateClass updateClass = new UpdateClass("Updated Test Class", "Updated Joseph");
        ClassOutput output = classService.putById(1L, updateClass);
        assertEquals(updateClass.name(), output.name());
        assertEquals(updateClass.instructor(), output.instructor());
    }

}
