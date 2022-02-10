package com.schegol.spring_project.repositories.dto;

import com.schegol.spring_project.entities.Passport;
import lombok.Data;

@Data
public class PersonDTO {
    private String surname;
    private String name;
    private String patronymic;
    private Integer age;
    private Passport passport;
}
