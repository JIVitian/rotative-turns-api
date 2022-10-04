package com.app.rotativeturnsapi.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class EmployeeDTO implements Serializable {

    private Long id;
    private String name;

    public EmployeeDTO(Long id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
