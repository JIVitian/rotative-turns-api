package com.app.rotativeturnsapi.mappers;

import com.app.rotativeturnsapi.dto.EmployeeDTO;
import com.app.rotativeturnsapi.entities.Employee;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapper {
    private final ModelMapper mapper = new ModelMapper();

    public EmployeeDTO entityToDTO(Employee entity) {
        return mapper.map(entity, EmployeeDTO.class);
    }

    public Employee DTOToEntity(EmployeeDTO dto) {
        return mapper.map(dto, Employee.class);
    }
}
