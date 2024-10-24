package com.grupo09.generation.controller;

import com.grupo09.generation.dto.in.RegisterStudent;
import com.grupo09.generation.dto.in.UpdateStudent;
import com.grupo09.generation.dto.out.StudentOutput;
import com.grupo09.generation.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }

    @PostMapping(path = "/register")
    public ResponseEntity<StudentOutput> registerStudent(@Valid @RequestBody RegisterStudent registerStudent, UriComponentsBuilder uriComponentsBuilder) {
        var output = this.studentService.save(registerStudent);
        var uri = uriComponentsBuilder.path("/classes/{id}").buildAndExpand(output.studentId()).toUri();
        return ResponseEntity.created(uri).body(output);
    }

    @GetMapping(path = "")
    public ResponseEntity<Iterable<StudentOutput>> findAll() {
        return ResponseEntity.ok(this.studentService.findAll());
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentOutput> findById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.studentService.findById(id));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<StudentOutput> update(@PathVariable("id") Long id, @Valid @RequestBody UpdateStudent updateStudent) {
        return ResponseEntity.ok().body(this.studentService.putById(id, updateStudent));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        this.studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

