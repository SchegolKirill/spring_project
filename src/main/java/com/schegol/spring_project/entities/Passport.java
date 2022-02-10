package com.schegol.spring_project.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "passports")
public class Passport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "series")
    private String series;

    @Column(name = "number")
    private String number;

    @Column(name = "date_of_issue")
    private LocalDate dateOfIssue;

    public Passport(String series, String number, LocalDate dateOfIssue) {
        this.series = series;
        this.number = number;
        this.dateOfIssue = dateOfIssue;
    }
}
