package com.schegol.spring_project.converters;

import com.schegol.spring_project.repositories.dto.RequestUserDTO;
import com.schegol.spring_project.entities.Person;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class RequestUserConverter {

    private final DepartmentConverter departmentConverter;

    public RequestUserDTO entityToDTO(Person person){
        RequestUserDTO dto = new RequestUserDTO();
        dto.setSurname(person.getSurname());
        dto.setName(person.getName());
        dto.setPatronymic(person.getPatronymic());
        dto.setAge(person.getAge());
        dto.setPassport(person.getPassport());
        dto.setCreationDate(person.getCreationDate());
        dto.setPassword(person.getPassword());
        dto.setMobile(person.getMobile());
        dto.setAddress(person.getAddress());
        //dto.setDepartmentDTO(departmentConverter.entityToDTO(person.getDepartment()));
        return dto;
    }

    public List<RequestUserDTO> entityToDTO(List<Person> persons){
        return persons.stream().map(p -> entityToDTO(p)).collect(Collectors.toList());
    }

    public Person DTOToEntity(RequestUserDTO dto) {
        Person person = new Person();
        person.setSurname(dto.getSurname());
        person.setName(dto.getName());
        person.setPatronymic(dto.getPatronymic());
        person.setAge(dto.getAge());
        person.setPassport(dto.getPassport());
        person.setCreationDate(dto.getCreationDate());
        person.setPassword(dto.getPassword());
        person.setMobile(dto.getMobile());
        person.setAddress(dto.getAddress());
        //person.setDepartment(departmentConverter.DTOToEntity(dto.getDepartmentDTO()));
        return person;
    }

    public List<Person> DTOToEntity (List<RequestUserDTO> dto){
        return dto.stream().map(p -> DTOToEntity(p)).collect(Collectors.toList());
    }
}
