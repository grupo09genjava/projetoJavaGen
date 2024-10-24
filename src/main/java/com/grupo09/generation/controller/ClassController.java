package com.grupo09.generation.controller;

import com.grupo09.generation.dto.in.RegisterClass;
import com.grupo09.generation.dto.in.UpdateClass;
import com.grupo09.generation.dto.out.ClassOutput;
import com.grupo09.generation.service.ClassService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
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

    @Operation(description = "Retrieve an class by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieve an class."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access to classes."),
            @ApiResponse(responseCode = "404", description = "Class not found."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @GetMapping(path = "/{id}")
    public ResponseEntity<ClassOutput> getClassById(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.classService.findById(id));
    }

    @Operation(description = "Fetches all registered classes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieves all classes"),
            @ApiResponse(responseCode = "401", description = "Unauthorized access to classes."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @GetMapping
    public ResponseEntity<Iterable<ClassOutput>> listClasses() {
        return ResponseEntity.ok().body(this.classService.findAll());
    }

    @Operation(description = "Create a new class")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Class created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided for class."),
            @ApiResponse(responseCode = "409", description = "A conflict occurred with the current state of the resource. " +
                    "Please check the data and try again."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @PostMapping(path = "/register")
    public ResponseEntity<ClassOutput> createClass(@Valid @RequestBody RegisterClass createClass, UriComponentsBuilder uriComponentsBuilder) {
        ClassOutput output = this.classService.save(createClass);
        var uri = uriComponentsBuilder.path("/createClass/{id}").buildAndExpand(output.classId()).toUri();
        return ResponseEntity.created(uri).body(output);
    }

    @Operation(description = "Update an class by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Class updated successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided for class."),
            @ApiResponse(responseCode = "404", description = "Class not found."),
            @ApiResponse(responseCode = "409", description = "A conflict occurred with the current state of the resource. " +
                    "Please check the data and try again."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @PutMapping(path = "/{id}")
    public ResponseEntity<ClassOutput> updateClass(@PathVariable("id") Long id, @RequestBody UpdateClass updateClass) {
        return ResponseEntity.ok().body(this.classService.putById(id, updateClass));
    }

    @Operation(description = "Deletes an class by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletes an class successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request. Invalid data provided for class."),
            @ApiResponse(responseCode = "404", description = "Class not found."),
            @ApiResponse(responseCode = "409", description = "A conflict occurred with the current state of the resource. " +
                    "Please check the data and try again."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteClassById(@PathVariable("id") Long id) {
        this.classService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


