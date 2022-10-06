package com.app.rotativeturnsapi.dto;


import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.io.Serializable;

@Getter
@NoArgsConstructor
public class EmployeeHoursDTO implements Serializable {
    @NotEmpty
    private String workdayType;

    @Positive
    private float hours;

    public EmployeeHoursDTO(String workdayType, float hours) {
        super();
        this.workdayType = workdayType;
        this.hours = hours;
    }
}
