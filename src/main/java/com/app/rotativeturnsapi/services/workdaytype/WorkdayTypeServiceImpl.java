package com.app.rotativeturnsapi.services.workdaytype;

import com.app.rotativeturnsapi.dto.WorkdayTypeDTO;
import com.app.rotativeturnsapi.entities.WorkdayType;
import com.app.rotativeturnsapi.mappers.WorkdayTypeMapper;
import com.app.rotativeturnsapi.repositories.WorkdayTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkdayTypeServiceImpl implements WorkdayTypeService {
    private final WorkdayTypeRepository repository;
    private final WorkdayTypeMapper mapper;

    @Override
    public List<WorkdayTypeDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(type -> mapper.entityToDTO(type))
                .collect(Collectors.toList());
    }

    @Override
    public WorkdayTypeDTO getById(Integer id) throws NoSuchElementException {
        WorkdayType type = repository.findById(id).get();

        return mapper.entityToDTO(type);
    }

    @Override
    public WorkdayTypeDTO getByName(String name) {
        return mapper.entityToDTO(repository.findByName(name));
    }

    @Override
    public WorkdayTypeDTO createWorkdayType(WorkdayTypeDTO workdayTypeDTO) {
        WorkdayType newType = mapper.DTOToEntity(workdayTypeDTO);

        return mapper.entityToDTO(repository.save(newType));
    }

    @Override
    public WorkdayTypeDTO updateWorkdayType(WorkdayTypeDTO workdayTypeDTO) {
        Optional<WorkdayType> typesToUpdate = repository.findById(workdayTypeDTO.getId());

        if (!typesToUpdate.isPresent()) {
            throw new NoSuchElementException();
        }

        WorkdayType typeToUpdate = typesToUpdate.get();

        typeToUpdate.setName(workdayTypeDTO.getName());

        return mapper.entityToDTO(typeToUpdate);
    }

    @Override
    public boolean deleteWorkdayType(Integer id) {
        if (repository.existsById(id)) {
            repository.existsById(id);

            return true;
        }

        return false;
    }
}
