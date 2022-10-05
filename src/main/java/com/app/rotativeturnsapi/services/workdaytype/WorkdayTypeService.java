package com.app.rotativeturnsapi.services.workdaytype;


import com.app.rotativeturnsapi.dto.WorkdayTypeDTO;
import com.app.rotativeturnsapi.entities.WorkdayType;

import java.util.List;

public interface WorkdayTypeService {
    public List<WorkdayTypeDTO> getAll();
    public WorkdayTypeDTO getById(Integer id);
    public WorkdayTypeDTO getByName(String name);
    public WorkdayTypeDTO createWorkdayType(WorkdayTypeDTO workdayTypeDTO);
    public WorkdayTypeDTO updateWorkdayType(WorkdayTypeDTO workdayTypeDTO);
    public boolean deleteWorkdayType(Integer id);
}
