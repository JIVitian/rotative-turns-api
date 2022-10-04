package com.app.rotativeturnsapi.services.employees;

import com.app.rotativeturnsapi.dto.EmployeeDTO;

import java.util.List;

public interface EmployeeService {
    public List<EmployeeDTO> getAll();
    public EmployeeDTO getById(Long id);
    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO);
}
