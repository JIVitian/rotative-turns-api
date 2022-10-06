package com.app.rotativeturnsapi.repositories;

import com.app.rotativeturnsapi.entities.Workday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkdayRepository extends JpaRepository<Workday, Long> {
    List<Workday> findWorkdaysByEmployeeIdAndDate(Long employeeId, LocalDate date);
    List<Workday> findWorkdaysByEmployeeIdAndDateBetween(Long employeeId, LocalDate dateFrom, LocalDate dateTo);
    List<Workday> findWorkdaysByDateBetween(LocalDate dateFrom, LocalDate dateTo);
    Workday findWorkdayByTypeIdAndDate(Integer typeId, LocalDate date);
    List<Workday> findWorkdaysByTypeIdAndDateBetween(Integer typeId, LocalDate dateFrom, LocalDate dateTo);
    List<Workday> findWorkdaysByEmployeeIdAndTypeId(Long employeeId, Integer typeId);
}
