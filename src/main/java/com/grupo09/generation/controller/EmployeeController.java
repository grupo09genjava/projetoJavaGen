package com.grupo09.generation.controller;

import com.grupo09.generation.dto.in.UpdateEmployee;
import com.grupo09.generation.dto.out.EmployeeOutput;
import com.grupo09.generation.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeOutput> getEmployee(@PathVariable("id") Long id) {
        return ResponseEntity.ok().body(this.employeeService.findById(id));
    }

    @Operation(description = "Fetch all registered employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the employees"),
    })
    @GetMapping()
    public ResponseEntity<Iterable<EmployeeOutput>> listEmployees() {
        return ResponseEntity.ok().body(this.employeeService.findAll());
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeOutput> updateEmployee(@PathVariable("id") Long id, @RequestBody UpdateEmployee employee) {
        return ResponseEntity.ok().body(this.employeeService.putById(id, employee));
    }

    @Operation(description = "Deletes an employee")
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id) {
        this.employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

