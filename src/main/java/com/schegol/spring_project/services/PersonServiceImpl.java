package com.schegol.spring_project.services;

import com.schegol.spring_project.entities.Person;
import com.schegol.spring_project.exception_handling.NoSuchPersonException;
import com.schegol.spring_project.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{

    private final PersonRepository personRepository;

    @Override
    public Person getPerson(Integer id) {
        return personRepository.findById(id).orElseThrow(() ->
                new NoSuchPersonException("There is no person with ID = " + id + " in database"));
    }

    @Override
    public void updatePerson(Person person, Integer id) {
        personRepository.findById(id).map(p->{
            p.setSurname(person.getSurname());
            p.setName(person.getName());
            p.setPatronymic(person.getPatronymic());
            p.setAge(person.getAge());
            p.setCreationDate(person.getCreationDate());
            p.setPassword(person.getPassword());
            p.setPassport(person.getPassport());
            p.setAddress(person.getAddress());
            return personRepository.save(p);
        }).orElseThrow(() ->
                new NoSuchPersonException("There is no person with ID = " + id + " in database"));
    }

    @Override
    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }

    @Override
    public void addPerson(Person person) {
        personRepository.save(person);
    }

    @Override
    public void addPersons(List<Person> persons) {
        personRepository.saveAll(persons);
    }
}
