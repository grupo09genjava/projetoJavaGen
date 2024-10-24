package com.grupo09.generation.controller;

import com.grupo09.generation.dto.in.RegisterClass;
import com.grupo09.generation.dto.in.UpdateClass;
import com.grupo09.generation.dto.out.ClassOutput;
import com.grupo09.generation.service.ClassService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/api/v1/classes")
public class ClassController {
    private final ClassService classService;

    public ClassController(ClassService classService){
        this.classService = classService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<ClassOutput> getClassById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.classService.findById(id));
    }

    @GetMapping
    public ResponseEntity<Iterable<ClassOutput>> listClasses() {
        return ResponseEntity.ok().body(this.classService.findAll());
    }

    @PostMapping(path = "/register")
    public ResponseEntity<ClassOutput> createClass(@Valid @RequestBody RegisterClass createClass, UriComponentsBuilder uriComponentsBuilder) {
        ClassOutput output = this.classService.save(createClass);
        var uri = uriComponentsBuilder.path("/createClass/{id}").buildAndExpand(output.classId()).toUri();
        return ResponseEntity.created(uri).body(output);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ClassOutput> updateClass(@PathVariable("id") Long id, @RequestBody UpdateClass updateClass) {
        return ResponseEntity.ok().body(this.classService.putById(id, updateClass));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteClassById(@PathVariable("id") Long id) {
        this.classService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


