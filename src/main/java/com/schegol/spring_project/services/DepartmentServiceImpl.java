package com.schegol.spring_project.services;

import com.schegol.spring_project.entities.Department;
import com.schegol.spring_project.entities.Person;
import com.schegol.spring_project.repositories.DepartmentRepository;
import com.schegol.spring_project.repositories.PersonRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService{

    private final DepartmentRepository departmentRepository;
    private final PersonRepository personRepository;

    @Override
    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Override
    public void createNewDepartment(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public void addPersonToDepartment(Integer personId, Integer departmentId) {
        departmentRepository.findById(departmentId).get()
                .addPersonToDepartment(personRepository.findById(personId).get());
        departmentRepository.save(departmentRepository.findById(departmentId).get());
        personRepository.save(personRepository.findById(personId).get());
    }

    @Override
    public void removePersonFromDepartment(Integer personId, Integer departmentId) {
        departmentRepository.findById(departmentId).get()
                .getPersonList().remove(personRepository.findById(personId).get());
        personRepository.findById(personId).get().setDepartment(null);
        departmentRepository.save(departmentRepository.findById(departmentId).get());
        personRepository.save(personRepository.findById(personId).get());
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        departmentRepository.deleteById(departmentId);
    }
}
