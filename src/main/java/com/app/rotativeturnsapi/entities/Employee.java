package com.app.rotativeturnsapi.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(name = "user_generator", initialValue = 100)
    private Long id;
    private String name;
}
