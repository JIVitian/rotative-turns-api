package com.app.rotativeturnsapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

    private Long id;
    @NotEmpty
    @Size(min = 2, message = "employee name should have at least 2 characters")
    private String name;

    public EmployeeDTO(Long id, String name) {
        super();
        id = id;
        name = name;
    }
}
