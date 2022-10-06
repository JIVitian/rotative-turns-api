package com.app.rotativeturnsapi.services.workday;

import com.app.rotativeturnsapi.dto.EmployeesHoursForEachWorkdayTypeDTO;
import com.app.rotativeturnsapi.dto.UpdateWorkdayDTO;
import com.app.rotativeturnsapi.dto.WorkdayDTO;

import java.util.List;

public interface WorkdayService {
    public List<WorkdayDTO> getAll();
    public WorkdayDTO getById(Long id);
    public List<EmployeesHoursForEachWorkdayTypeDTO> getHoursChargedForEveryEmployeeForEachType();
    public WorkdayDTO createWorkday(WorkdayDTO workdayDTO);
    public WorkdayDTO updateWorkday(UpdateWorkdayDTO workday);
    public boolean deleteWorkday(Long id);
}
