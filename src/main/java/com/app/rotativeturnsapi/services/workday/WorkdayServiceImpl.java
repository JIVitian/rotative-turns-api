package com.app.rotativeturnsapi.services.workday;

import com.app.rotativeturnsapi.dto.EmployeeHoursDTO;
import com.app.rotativeturnsapi.dto.EmployeesHoursForEachWorkdayTypeDTO;
import com.app.rotativeturnsapi.dto.UpdateWorkdayDTO;
import com.app.rotativeturnsapi.dto.WorkdayDTO;
import com.app.rotativeturnsapi.entities.Employee;
import com.app.rotativeturnsapi.entities.Workday;
import com.app.rotativeturnsapi.entities.WorkdayType;
import com.app.rotativeturnsapi.mappers.WorkdayMapper;
import com.app.rotativeturnsapi.repositories.EmployeeRepository;
import com.app.rotativeturnsapi.repositories.WorkdayRepository;
import com.app.rotativeturnsapi.repositories.WorkdayTypeRepository;
import com.app.rotativeturnsapi.services.businesslogic.BusinessLogicValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkdayServiceImpl implements WorkdayService {

    private final WorkdayRepository workdayRepository;
    private final WorkdayTypeRepository typeRepository;
    private final EmployeeRepository employeeRepository;
    private final WorkdayMapper mapper;
    private final BusinessLogicValidator businessLogicValidator;

    @Override
    public List<WorkdayDTO> getAll() {
        return workdayRepository.findAll().stream().map(mapper::entityToDTO).collect(Collectors.toList());
    }

    @Override
    public WorkdayDTO getById(Long id) {
        return mapper.entityToDTO(workdayRepository.findById(id).get());
    }

    @Override
    public List<EmployeesHoursForEachWorkdayTypeDTO> getHoursChargedForEveryEmployeeForEachType() {
        List<Employee> employees = employeeRepository.findAll();
        List<WorkdayType> types = typeRepository.findAll();
        List<EmployeesHoursForEachWorkdayTypeDTO> response = new ArrayList<>();

        for (Employee employee : employees) {
            List<EmployeeHoursDTO> employeeHours = new ArrayList<>();

            for (WorkdayType type : types) {
                List<Workday> workdays = workdayRepository.findWorkdaysByEmployeeIdAndTypeId(employee.getId(), type.getId());

                long totalWorkingHours = 0;

                for (Workday workday : workdays) {
                    totalWorkingHours += ChronoUnit.HOURS.between(workday.getTimeFrom(), workday.getTimeTo());
                }

                employeeHours.add(new EmployeeHoursDTO(type.getName(), totalWorkingHours));
            }

            response.add(new EmployeesHoursForEachWorkdayTypeDTO(employee, employeeHours));
        }

        return response;
    }

    @Override
    public WorkdayDTO createWorkday(WorkdayDTO workdayDTO) throws IllegalArgumentException {
        Workday newWorkday = mapper.DTOToEntity(workdayDTO);

        businessLogicValidator.ValidateWorkdayCreate(newWorkday);

        return mapper.entityToDTO(workdayRepository.save(newWorkday));
    }

    @Override
    public WorkdayDTO updateWorkday(UpdateWorkdayDTO workday) {
        if (!this.workdayRepository.existsById(workday.getId())) {
            throw new NoSuchElementException();
        }

        Workday workdayToUpdate = workdayRepository.findById(workday.getId()).get();

        workdayToUpdate.setTimeFrom(workday.getTimeFrom());
        workdayToUpdate.setTimeTo(workday.getTimeTo());

        return mapper.entityToDTO(workdayToUpdate);
    }

    @Override
    public boolean deleteWorkday(Long id) {
        if(workdayRepository.existsById(id)) {
            workdayRepository.deleteById(id);
            return true;
        }

        return false;
    }
}
