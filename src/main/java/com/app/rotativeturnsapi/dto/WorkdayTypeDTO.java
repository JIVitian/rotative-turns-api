package com.app.rotativeturnsapi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class WorkdayTypeDTO implements Serializable {
    private Integer id;
    private String name;

    public WorkdayTypeDTO(Integer id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}
