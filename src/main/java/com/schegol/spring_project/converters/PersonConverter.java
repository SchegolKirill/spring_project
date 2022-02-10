package com.schegol.spring_project.converters;

import com.schegol.spring_project.repositories.dto.PersonDTO;
import com.schegol.spring_project.entities.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter {
    public PersonDTO entityToDTO(Person person){
        PersonDTO dto = new PersonDTO();
        dto.setName(person.getName());
        dto.setSurname(person.getSurname());
        dto.setPatronymic(person.getPatronymic());
        dto.setAge(person.getAge());
        dto.setPassport(person.getPassport());
        return dto;
    }

    public List<PersonDTO> entityToDTO(List<Person> personList){
        return personList.stream().map(p -> entityToDTO(p)).collect(Collectors.toList());
    }

    public Person DTOToEntity(PersonDTO dto) {
        Person person = new Person();
        person.setName(dto.getName());
        person.setSurname(dto.getSurname());
        person.setPatronymic(dto.getPatronymic());
        person.setAge(dto.getAge());
        person.setPassport(dto.getPassport());
        return person;
    }

    public List<Person> DTOToEntity(List<PersonDTO> dto){
        return dto.stream().map(p -> DTOToEntity(p)).collect(Collectors.toList());
    }
}
