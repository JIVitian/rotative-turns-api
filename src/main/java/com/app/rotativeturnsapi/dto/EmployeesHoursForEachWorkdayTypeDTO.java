package com.app.rotativeturnsapi.dto;


import com.app.rotativeturnsapi.entities.Employee;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class EmployeesHoursForEachWorkdayTypeDTO implements Serializable {
    @NotNull()
    private Employee employee;
    private List<EmployeeHoursDTO> employeeHoursForEachWorkdayType;

    public EmployeesHoursForEachWorkdayTypeDTO(Employee employee, List<EmployeeHoursDTO> employeeHoursForEachWorkdayType) {
        super();
        this.employee = employee;
        this.employeeHoursForEachWorkdayType = employeeHoursForEachWorkdayType;
    }
}
