package com.grupo09.generation.controller;

import com.grupo09.generation.dto.in.UpdateEmployee;
import com.grupo09.generation.dto.out.EmployeeOutput;
import com.grupo09.generation.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController{
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @Operation(description = "Retrieves an employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieves an employee."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access to employee."),
            @ApiResponse(responseCode = "404", description = "Employee not found."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeOutput> getEmployeeById(@PathVariable("id") Long id){
        return ResponseEntity.ok().body(this.employeeService.findById(id));
    }

    @Operation(description = "Fetch all registered employees")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Retrieves all employees."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access to employee."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @GetMapping()
    public ResponseEntity<Iterable<EmployeeOutput>> listEmployees(){
        return ResponseEntity.ok().body(this.employeeService.findAll());
    }

    @Operation(description = "Update an employee by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee updated successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access to employee."),
            @ApiResponse(responseCode = "404", description = "Employee not found."),
            @ApiResponse(responseCode = "409", description = "A conflict occurred with the current state of the resource. " +
                    "Please check the data and try again."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @PutMapping(path = "/{id}")
    public ResponseEntity<EmployeeOutput> updateEmployee(@PathVariable("id") Long id,@RequestBody UpdateEmployee employee){
        return ResponseEntity.ok().body(this.employeeService.putById(id,employee));
    }

    @Operation(description = "Deletes an employee")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Deletes an employee successfully."),
            @ApiResponse(responseCode = "401", description = "Unauthorized access to employee."),
            @ApiResponse(responseCode = "404", description = "Employee not found."),
            @ApiResponse(responseCode = "500", description = "An unexpected error occurred on the server. " +
                    "Please try again later or contact support if the issue persists."),})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable("id") Long id){
        this.employeeService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

