package com.app.rotativeturnsapi.controllers;

import com.app.rotativeturnsapi.dto.EmployeeDTO;
import com.app.rotativeturnsapi.services.employees.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @GetMapping("all")
    public ResponseEntity<List<EmployeeDTO>> getAll(){
        return ResponseEntity.ok(this.employeeService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<EmployeeDTO> getById(@PathVariable(name = "id", required = false) Long id) {
        return ResponseEntity.ok(this.employeeService.getById(id));
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDTO> crearEmpleado(@RequestBody EmployeeDTO employee){
        EmployeeDTO newEmployee = this.employeeService.createEmployee(employee);

        return ResponseEntity.ok(newEmployee);
    }

}
