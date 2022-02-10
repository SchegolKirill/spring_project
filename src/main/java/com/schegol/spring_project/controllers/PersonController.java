package com.schegol.spring_project.controllers;

import com.schegol.spring_project.converters.PersonConverter;
import com.schegol.spring_project.converters.RequestUserConverter;
import com.schegol.spring_project.repositories.dto.PersonDTO;
import com.schegol.spring_project.repositories.dto.RequestUserDTO;
import com.schegol.spring_project.services.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/person")
@RequiredArgsConstructor
public class PersonController {

    private final PersonService personService;
    private final PersonConverter personConverter;
    private final RequestUserConverter requestUserConverter;

    @GetMapping("/getperson/{id}")
    public PersonDTO getPerson(@PathVariable("id") Integer id){
        PersonDTO dto = personConverter.entityToDTO(personService.getPerson(id));
        return dto;
    }

    @PostMapping("/addperson")
    public String addNewPerson(@RequestBody RequestUserDTO dto){
        personService.addPerson(requestUserConverter.DTOToEntity(dto));
        return "Person successfully added";
    }

    @PostMapping("/addpersons")
    public String addNewPersons(@RequestBody List<RequestUserDTO> dto) {
        personService.addPersons(requestUserConverter.DTOToEntity(dto));
        return "Persons successfully added";
    }

    @PutMapping("/updateperson/{id}")
    public String updatePerson(@RequestBody RequestUserDTO dto, @PathVariable Integer id){
        personService.updatePerson(requestUserConverter.DTOToEntity(dto), id);
        return "Person successfully updated";
    }

    @DeleteMapping("deleteperson/{id}")
    public String deletePerson(@PathVariable Integer id){
        personService.deletePerson(id);
        return "Person successfully deleted";
    }
}
