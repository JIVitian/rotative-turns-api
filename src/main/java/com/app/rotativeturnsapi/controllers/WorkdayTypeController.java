package com.app.rotativeturnsapi.controllers;

import com.app.rotativeturnsapi.dto.WorkdayTypeDTO;
import com.app.rotativeturnsapi.services.workdaytype.WorkdayTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("workday/type")
@RequiredArgsConstructor
public class WorkdayTypeController {
    private final WorkdayTypeService workdayTypeService;

    @GetMapping("all")
    public ResponseEntity<List<WorkdayTypeDTO>> getAll() {
        return ResponseEntity.ok().body(workdayTypeService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<WorkdayTypeDTO> getById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(workdayTypeService.getById(id));
    }

    @PostMapping
    public ResponseEntity<WorkdayTypeDTO> createWorkdayType(@RequestBody WorkdayTypeDTO workdayTypeDTO) {
        return ResponseEntity.ok().body(workdayTypeService.createWorkdayType(workdayTypeDTO));
    }

    @PatchMapping("update")
    public ResponseEntity<WorkdayTypeDTO> updateWorkdayType(@RequestBody WorkdayTypeDTO workdayTypeDTO) {
        return ResponseEntity.ok().body(workdayTypeService.updateWorkdayType(workdayTypeDTO));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity deleteWorkdayType(@PathVariable Integer id) {
        return ResponseEntity.ok().body(workdayTypeService.deleteWorkdayType(id));
    }
}
