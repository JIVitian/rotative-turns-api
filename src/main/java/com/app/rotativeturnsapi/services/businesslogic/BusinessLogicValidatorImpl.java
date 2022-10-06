package com.app.rotativeturnsapi.services.businesslogic;

import com.app.rotativeturnsapi.entities.Workday;
import com.app.rotativeturnsapi.exceptions.AmountOfEmployeesException;
import com.app.rotativeturnsapi.exceptions.AmountOfFreeDaysPerWeekException;
import com.app.rotativeturnsapi.exceptions.EmployeeHasTheDayOffException;
import com.app.rotativeturnsapi.exceptions.WorkdayDurationException;
import com.app.rotativeturnsapi.repositories.WorkdayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.time.temporal.WeekFields;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import static java.util.Objects.isNull;

@Service
@RequiredArgsConstructor
public class BusinessLogicValidatorImpl implements BusinessLogicValidator {

    private final WorkdayRepository workdayRepository;

    private final PrincipalWorkdayTypes freeDayId = PrincipalWorkdayTypes.valueOf("FREE_DAY");
    private final PrincipalWorkdayTypes vacationsId = PrincipalWorkdayTypes.valueOf("VACATIONS");

    @Override
    public boolean ValidateWorkdayCreate(Workday newWorkday) throws IllegalArgumentException {
        if(!isWorkdayDurationValid(newWorkday)) {
            throw new WorkdayDurationException("The duration of the new Workday should be between 6 and 8 hours for a new Normal Workday and between 2 and 6 hours for overtime.");
        }

        if(!isHoursWorkedPerDayValid(newWorkday)) {
            throw new WorkdayDurationException("The employee may not work more than 12 hours per day.");
        }

        if(!isAmountOfHoursWorkedInTheWeekValid(newWorkday)) {
            throw new WorkdayDurationException("The employee may not work more than 48 hours per week.");
        }

        if(!isAmountOfEmployeesForWorkdayValid(newWorkday)) {
            throw new AmountOfEmployeesException("One turn cannot have more than 2 employees at a time.");
        }

        if(newWorkday.getType().getId().equals(freeDayId.getId()) && !isThereAvailableFreeDays(newWorkday)) {
            throw new AmountOfFreeDaysPerWeekException("The employee cannot have more days off this week.");
        }

        if(!employeeHasNoFreeDay(newWorkday)) {
            throw new EmployeeHasTheDayOffException("The employee has the day off on the date " + newWorkday.getDate().toString());
        }

        return true;
    }

    private boolean isWorkdayDurationValid(Workday newWorkday) {
        long duration = getWorkdayDuration(newWorkday);
        PrincipalWorkdayTypes workdayTypes = PrincipalWorkdayTypes.values()[newWorkday.getType().getId() - 1];

        switch (workdayTypes) {
            case NORMAL:
                return duration >= 6 && duration <= 8;
            case EXTRA_HOURS:
                return duration >= 2 && duration <= 6;
            default:
                return true;
        }
    }

    private boolean isHoursWorkedPerDayValid(Workday newWorkday) {
        // If the employee is on vacations, these hours doesn't matter
        if(!employeeIsNotInVacationsOrFreeDay(newWorkday)) {
            return true;
        }

        List<Workday> employeesWorkdaysOfTheDate = workdayRepository.findWorkdaysByEmployeeIdAndDate(newWorkday.getEmployee().getId(), newWorkday.getDate());

        List<Workday> everyWorkdayOfTheDate = filterByWorkedDays(employeesWorkdaysOfTheDate);

        long hoursSum = getWorkdayDuration(newWorkday);
        long hoursWorkedInTheDate = getHoursWorkedFromAList(everyWorkdayOfTheDate);

        return hoursWorkedInTheDate + hoursSum <= 12;
    }

    private boolean isAmountOfHoursWorkedInTheWeekValid(Workday newWorkday) {
        if(!employeeIsNotInVacationsOrFreeDay(newWorkday)) {
            return true;
        }

        List<Workday> workdaysOfTheWeek = getWorkdaysOfTheWeekForEmployee(newWorkday);

        long hoursWorkedInTheWeek = getHoursWorkedFromAList(workdaysOfTheWeek);

        long newWorkdayDuration = ChronoUnit.HOURS.between(newWorkday.getTimeFrom(), newWorkday.getTimeTo());

        long hoursToJobInTheWeek = hoursWorkedInTheWeek + newWorkdayDuration;

        // The hours worked in the week needs to less or equals than 48
        return hoursToJobInTheWeek <= 48;
    }

    private boolean isAmountOfEmployeesForWorkdayValid(Workday newWorkday) {
        if(!employeeIsNotInVacationsOrFreeDay(newWorkday)) {
            return true;
        }

        List<Workday> workdaysOfTheWeek = getAllWorkdaysInAWeek(newWorkday);

        // I consider two workdays are overlapped if they are overlapped in the time
        List<Workday> workdaysInTheSameTurn = workdaysOfTheWeek
                .stream()
                .filter(workday -> !workday.getId().equals(newWorkday.getId()) &&
                        (oneHourIsBetween(newWorkday.getTimeFrom(), workday.getTimeFrom(), workday.getTimeTo()) ||
                         oneHourIsBetween(newWorkday.getTimeTo(), workday.getTimeFrom(), workday.getTimeTo())))
                .collect(Collectors.toList());

        return  workdaysInTheSameTurn.size() <= 2;
    }

    private boolean employeeHasNoFreeDay(Workday newWorkday) {
        Workday freeDay = workdayRepository.findWorkdayByTypeIdAndDate(freeDayId.getId(), newWorkday.getDate());
        Workday vacationsDay = workdayRepository.findWorkdayByTypeIdAndDate(vacationsId.getId(), newWorkday.getDate());

        return isNull(freeDay) && isNull(vacationsDay);
    }

    private boolean isThereAvailableFreeDays(Workday newWorkday) {
        final DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        final DayOfWeek lastDayOfWeek = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);

        LocalDate firstWorkdayOfTheWeek = newWorkday.getDate().with(TemporalAdjusters.previousOrSame(firstDayOfWeek));
        LocalDate lastWorkdayOfTheWeek = newWorkday.getDate().with(TemporalAdjusters.nextOrSame(lastDayOfWeek));

        List<Workday> freeDaysToWork = workdayRepository.findWorkdaysByTypeIdAndDateBetween(freeDayId.getId(), firstWorkdayOfTheWeek, lastWorkdayOfTheWeek);

        return freeDaysToWork.size() < 2;
    }

    private long getWorkdayDuration(Workday newWorkday) {
        return ChronoUnit.HOURS.between(newWorkday.getTimeFrom(), newWorkday.getTimeTo());
    }

    private boolean employeeIsNotInVacationsOrFreeDay(Workday newWorkday) {
        return !newWorkday.getType().getId().equals(freeDayId) && !newWorkday.getType().getId().equals(vacationsId);
    }

    private List<Workday> filterByWorkedDays(List<Workday> workdayList) {
        return workdayList
                .stream()
                .filter(this::employeeIsNotInVacationsOrFreeDay)
                .collect(Collectors.toList());
    }

    private long getHoursWorkedFromAList(List<Workday> workdayList) {
        long totalWorkedHours = 0;

        for(Workday workday : workdayList) {
            totalWorkedHours = getWorkdayDuration(workday);
        }

        return totalWorkedHours;
    }

    private List<Workday> getWorkdaysOfTheWeekForEmployee(Workday newWorkday) {
        final DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        final DayOfWeek lastDayOfWeek = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);

        LocalDate firstWorkdayOfTheWeek = newWorkday.getDate().with(TemporalAdjusters.previousOrSame(firstDayOfWeek));
        LocalDate lastWorkdayOfTheWeek = newWorkday.getDate().with(TemporalAdjusters.nextOrSame(lastDayOfWeek));

        List<Workday> workadaysOfTheWeek = workdayRepository.findWorkdaysByEmployeeIdAndDateBetween(newWorkday.getId(), firstWorkdayOfTheWeek, lastWorkdayOfTheWeek);

        return filterByWorkedDays(workadaysOfTheWeek);
    }
    
    private List<Workday> getAllWorkdaysInAWeek(Workday newWorkday) {
        final DayOfWeek firstDayOfWeek = WeekFields.of(Locale.getDefault()).getFirstDayOfWeek();
        final DayOfWeek lastDayOfWeek = DayOfWeek.of(((firstDayOfWeek.getValue() + 5) % DayOfWeek.values().length) + 1);

        LocalDate firstWorkdayOfTheWeek = newWorkday.getDate().with(TemporalAdjusters.previousOrSame(firstDayOfWeek));
        LocalDate lastWorkdayOfTheWeek = newWorkday.getDate().with(TemporalAdjusters.nextOrSame(lastDayOfWeek));
        
        List<Workday> workadaysOfTheWeek = workdayRepository.findWorkdaysByDateBetween(firstWorkdayOfTheWeek, lastWorkdayOfTheWeek);

        return workadaysOfTheWeek
                .stream()
                .filter(workday -> employeeIsNotInVacationsOrFreeDay(workday))
                .collect(Collectors.toList());
    }

    private boolean oneHourIsBetween(LocalTime timeToCompare, LocalTime timeFrom, LocalTime timeTo) {
        return timeFrom.compareTo(timeToCompare) <= 0 && timeTo.compareTo(timeToCompare) >= 0;
    }
}
