package com.schegol.spring_project.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.schegol.spring_project.converters.PersonConverter;
import com.schegol.spring_project.converters.RequestUserConverter;
import com.schegol.spring_project.repositories.dto.PersonDTO;
import com.schegol.spring_project.repositories.dto.RequestUserDTO;
import com.schegol.spring_project.entities.Passport;
import com.schegol.spring_project.entities.Person;
import com.schegol.spring_project.repositories.PersonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class PersonControllerTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonController personController;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PersonConverter personConverter;

    @Autowired
    private RequestUserConverter requestUserConverter;

    @Test
    void getPerson() throws Exception {
        Person testPerson = new Person("Schegol", "Kirill", 26, "Borisovich",
                LocalDate.of(2022, 2, 9), "asd",
                new Passport("4016", "467697", LocalDate.of(2016, 2, 16)),
                "Saratov", "89210900302");
        Person savedPerson = personRepository.save(testPerson);

        mockMvc.perform(get("/person/getperson/" + savedPerson.getId()))
                .andExpect(status().isOk())
                .andExpect(clientCheck("$", personController.getPerson(testPerson.getId())));

    }

    @Test
    void addPerson() throws Exception {
        Person testPerson = new Person("Schegol", "Kirill", 26, "Borisovich",
                LocalDate.of(2022, 2, 9), "asd",
                new Passport("4016", "467697", LocalDate.of(2016, 2, 16)),
                "Saratov", "89210900302");

        mockMvc.perform(post("/person/addperson")
                .content(objectMapper.writeValueAsString(testPerson))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void addNewPersons () throws Exception{
        List<Person> persons = new ArrayList<>();

        Person testPerson1 = new Person("Schegol", "Kirill", 26, "Borisovich",
                LocalDate.of(2022, 2, 9), "asd",
                new Passport("4016", "467697", LocalDate.of(2016, 2, 16)),
                "Saratov", "89210900302");
        Person testPerson2 = new Person("Schegol2", "Kirill2", 262, "Borisovich2",
                LocalDate.of(2022, 2, 9), "asd2",
                new Passport("4015", "467696", LocalDate.of(2016, 2, 16)),
                "Saratov2", "+79210900302");

        persons.add(testPerson1);
        persons.add(testPerson2);

        mockMvc.perform(post("/person/addpersons")
                .content(objectMapper.writeValueAsString(persons))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void updatePerson() throws Exception{
        Person testPerson1 = new Person("Schegol", "Kirill", 26, "Borisovich",
                LocalDate.of(2022, 2, 9), "asd",
                new Passport("4016", "467697", LocalDate.of(2016, 2, 16)),
                "Saratov", "89210900302");
        personRepository.save(testPerson1);
        Person testPerson2 = new Person("Schegol2", "Kirill2", 262, "Borisovich2",
                LocalDate.of(2022, 2, 9), "asd2",
                new Passport("4015", "467696", LocalDate.of(2016, 2, 16)),
                "Saratov2", "+79210900302");
        RequestUserDTO testPerson2DTO = requestUserConverter.entityToDTO(testPerson2);

        mockMvc.perform(put("/person/updateperson/{id}", testPerson1.getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(testPerson2DTO)))
                .andExpect(status().isOk());

        Optional<Person> personCheck = personRepository.findById(testPerson1.getId());
        if (personCheck.isPresent()) {
            assert personCheck.get().getSurname().equals(testPerson2DTO.getSurname());
            assert personCheck.get().getName().equals(testPerson2DTO.getName());
            assert personCheck.get().getAge().equals(testPerson2DTO.getAge());
        }
    }

    public static ResultMatcher clientCheck(String prefix, PersonDTO personDTO) {
        return ResultMatcher.matchAll(
                jsonPath(prefix + ".surname").value(personDTO.getSurname()),
                jsonPath(prefix + ".name").value(personDTO.getName()),
                jsonPath(prefix + ".patronymic").value(personDTO.getPatronymic()),
                jsonPath(prefix + ".age").value(personDTO.getAge()),
                jsonPath(prefix + ".passport.series").value(personDTO.getPassport().getSeries())
        );
    }


}
