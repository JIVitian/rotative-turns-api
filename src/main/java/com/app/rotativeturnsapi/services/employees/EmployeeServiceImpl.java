package com.app.rotativeturnsapi.services.employees;

import com.app.rotativeturnsapi.dto.EmployeeDTO;
import com.app.rotativeturnsapi.entities.Employee;
import com.app.rotativeturnsapi.mappers.EmployeeMapper;
import com.app.rotativeturnsapi.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public List<EmployeeDTO> getAll() {
        List<Employee> employees = repository.findAll();

        return employees
                .stream()
                .map(employee -> mapper.entityToDTO(employee))
                .collect(Collectors.toList());
    }

    public EmployeeDTO getById(Long id) {
        return mapper.entityToDTO(repository.findById(id).get());
    }

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        Employee employee = mapper.DTOToEntity(employeeDTO);

        return mapper.entityToDTO(repository.save(employee));
    }

}
