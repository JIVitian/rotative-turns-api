package com.app.rotativeturnsapi.mappers;

import com.app.rotativeturnsapi.dto.WorkdayTypeDTO;
import com.app.rotativeturnsapi.entities.WorkdayType;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class WorkdayTypeMapper {
    private final ModelMapper mapper = new ModelMapper();

    public WorkdayTypeDTO entityToDTO(WorkdayType entity) {
        return mapper.map(entity, WorkdayTypeDTO.class);
    }

    public WorkdayType DTOToEntity(WorkdayTypeDTO dto) {
        return mapper.map(dto, WorkdayType.class);
    }
}
