package com.schegol.spring_project.repositories.dto;

import com.schegol.spring_project.entities.Passport;
import lombok.Data;

import java.time.LocalDate;

@Data
public class RequestUserDTO {

    private String surname;
    private String name;
    private Integer age;
    private String patronymic;
    private LocalDate creationDate;
    private String password;
    private Passport passport;
    private String address;
    private String mobile;
//    private DepartmentDTO departmentDTO;
}
