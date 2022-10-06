package com.app.rotativeturnsapi.controllers;

import com.app.rotativeturnsapi.dto.EmployeesHoursForEachWorkdayTypeDTO;
import com.app.rotativeturnsapi.dto.UpdateWorkdayDTO;
import com.app.rotativeturnsapi.dto.WorkdayDTO;
import com.app.rotativeturnsapi.services.workday.WorkdayService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("workday")
public class WorkdayController {
    private final WorkdayService service;

    @GetMapping("all")
    public ResponseEntity<List<WorkdayDTO>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkdayDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.getById(id));
    }

    @GetMapping("list/hours-per-workday")
    public ResponseEntity<List<EmployeesHoursForEachWorkdayTypeDTO>> getHoursPerWorkday() {
        return ResponseEntity.ok().body(service.getHoursChargedForEveryEmployeeForEachType());
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<WorkdayDTO> createWorkday(@RequestBody WorkdayDTO workdayDTO) {
        return ResponseEntity.ok().body(service.createWorkday(workdayDTO));
    }

    @PatchMapping("update")
    public ResponseEntity<WorkdayDTO> updateWorkday(@RequestBody UpdateWorkdayDTO workday) {
        return ResponseEntity.ok().body(service.updateWorkday(workday));
    }

    @DeleteMapping("delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Boolean> deleteWorkday(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.deleteWorkday(id));
    }
}
