package com.schegol.spring_project.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "surname")
    private String surname;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "passport_id")
    private Passport passport;

    @Column(name = "address")
    private String address;

    @Column(name = "mobile")
    private String mobile;

    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.DETACH,
            CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "department_id")
    private Department department;

    public Person(String surname, String name, Integer age, String patronymic, LocalDate creationDate,
                  String password, Passport passport, String address, String mobile) {
        this.surname = surname;
        this.name = name;
        this.age = age;
        this.patronymic = patronymic;
        this.creationDate = creationDate;
        this.password = password;
        this.passport = passport;
        this.address = address;
        this.mobile = mobile;
    }
}
