package com.app.rotativeturnsapi.mappers;

import com.app.rotativeturnsapi.dto.WorkdayDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WorkdayMapper {
    private final ModelMapper mapper = new ModelMapper();

    public WorkdayDTO entityToDTO(com.app.rotativeturnsapi.entities.Workday entity) {
        return mapper.map(entity, WorkdayDTO.class);
    }

    public com.app.rotativeturnsapi.entities.Workday DTOToEntity(WorkdayDTO dto) {
        return mapper.map(dto, com.app.rotativeturnsapi.entities.Workday.class);
    }
}
