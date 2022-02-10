package com.schegol.spring_project.services;

import com.schegol.spring_project.entities.Person;

import java.util.List;

public interface PersonService {

    Person getPerson(Integer id);

    void updatePerson(Person person,Integer id);

    void deletePerson(Integer id);

    void addPerson(Person person);

    void addPersons(List<Person> persons);
}
