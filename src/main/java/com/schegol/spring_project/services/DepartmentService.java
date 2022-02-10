package com.schegol.spring_project.services;

import com.schegol.spring_project.entities.Department;
import com.schegol.spring_project.entities.Person;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    List<Department> getAllDepartments();

    void createNewDepartment(Department department);

    void addPersonToDepartment(Integer personId, Integer departmentId);

    void removePersonFromDepartment(Integer personId, Integer departmentId);

    void deleteDepartment(Integer departmentId);
}
