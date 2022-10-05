package com.app.rotativeturnsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class WorkdayType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Setter
    @Column(nullable = false)
    private String name;
}
