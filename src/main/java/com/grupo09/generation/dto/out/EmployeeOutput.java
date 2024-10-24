package com.grupo09.generation.dto.out;

import com.grupo09.generation.model.EmployeeModel;

public record EmployeeOutput(
        Long employeeId,
        String name,
        String email,
        String jobTitle
) {
    public static EmployeeOutput fromEntity(EmployeeModel employeeModel) {
        return new EmployeeOutput(
                employeeModel.getId(),
                employeeModel.getName(),
                employeeModel.getEmail(),
                employeeModel.getJobTitle()
        );
    }
}
