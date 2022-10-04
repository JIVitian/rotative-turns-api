package com.app.rotativeturnsapi.services.employees;

import com.app.rotativeturnsapi.dto.EmployeeDTO;
import com.app.rotativeturnsapi.entities.Employee;
import com.app.rotativeturnsapi.repositories.EmployeeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private Employee employee;

    ModelMapper modelMapper = new ModelMapper();

    public List<EmployeeDTO> getAll() {
        List<Employee> employees = this.repository.findAll();

        return employees
                .stream()
                .map(employee -> this.modelMapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getById(Long id) {
        Optional<Employee> employee = this.repository.findById(id);

        if(!employee.isPresent()) {
            return null;
        }

        return this.modelMapper.map(employee.get(), EmployeeDTO.class);
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        this.employee = this.modelMapper.map(employeeDTO, Employee.class);

        return this.modelMapper.map(this.repository.save(this.employee), EmployeeDTO.class);
    }

}
