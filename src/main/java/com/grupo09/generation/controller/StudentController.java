package com.grupo09.generation.controller;

import com.grupo09.generation.dto.in.RegisterStudent;
import com.grupo09.generation.dto.in.UpdateStudent;
import com.grupo09.generation.dto.out.StudentOutput;
import com.grupo09.generation.service.StudentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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

    @Operation(description = "Create a new student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Student created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided for student."),
            @ApiResponse(responseCode = "409", description = "A conflict occurred with the current state of the resource. " +
                    "Please check the data and try again."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),}
    )
    @PostMapping(path = "/register")
    public ResponseEntity<StudentOutput> registerStudent(@Valid @RequestBody RegisterStudent registerStudent, UriComponentsBuilder uriComponentsBuilder) {
        var output = this.studentService.save(registerStudent);
        var uri = uriComponentsBuilder.path("/classes/{id}").buildAndExpand(output.studentId()).toUri();
        return ResponseEntity.created(uri).body(output);
    }
    @Operation(description = "Fetches all registered students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieves all students"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access to students."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @GetMapping
    public ResponseEntity<Iterable<StudentOutput>> listStudents() {
        return ResponseEntity.ok(this.studentService.findAll());
    }
    @Operation(description = "Retrieves an student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieves an student."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access to students."),
            @ApiResponse(responseCode = "404", description = "Student not found."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentOutput> getStudentById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.studentService.findById(id));
    }
    @Operation(description = "Update an student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student updated successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided for student."),
            @ApiResponse(responseCode = "404", description = "Student not found."),
            @ApiResponse(responseCode = "409", description = "A conflict occurred with the current state of the resource. " +
                    "Please check the data and try again."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @PutMapping(path = "/{id}")
    public ResponseEntity<StudentOutput> updateStudent(@PathVariable("id") Long id, @Valid @RequestBody UpdateStudent updateStudent) {
        return ResponseEntity.ok().body(this.studentService.putById(id, updateStudent));
    }

    @Operation(description = "Deletes an student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletes an student successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided for student."),
            @ApiResponse(responseCode = "404", description = "Student not found."),
            @ApiResponse(responseCode = "409", description = "A conflict occurred with the current state of the resource. " +
                    "Please check the data and try again."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long id) {
        this.studentService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

