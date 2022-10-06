package com.app.rotativeturnsapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class WorkdayDTO implements Serializable {
    private Long id;

    @NotNull
    private EmployeeDTO employee;

    @NotNull
    private WorkdayTypeDTO type;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate date;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime timeFrom;

    @JsonSerialize(using = LocalTimeSerializer.class)
    @JsonDeserialize(using = LocalTimeDeserializer.class)
    @JsonFormat(pattern = "HH:mm:ss")
    private LocalTime timeTo;

    public WorkdayDTO(
            Long id,
            EmployeeDTO employee,
            WorkdayTypeDTO type,
            LocalDate date,
            LocalTime timeFrom,
            LocalTime timeTo
    ) {
        super();
        this.id = id;
        this.employee = employee;
        this.type = type;
        this.date = date;
        this.timeFrom = timeFrom;
        this.timeTo = timeTo;
    }
}
