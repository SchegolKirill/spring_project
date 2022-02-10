package com.schegol.spring_project.converters;

import com.schegol.spring_project.repositories.dto.DepartmentDTO;
import com.schegol.spring_project.entities.Department;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class DepartmentConverter {

    private final PersonConverter personConverter;

    public DepartmentDTO entityToDTO(Department department){
        DepartmentDTO dto = new DepartmentDTO();
        dto.setName(department.getName());
        //dto.setPersons(personConverter.entityToDTO(department.getPersonList()));
        return dto;
    }

    public List<DepartmentDTO> entityToDTO (List<Department> departments) {
        return departments.stream().map(d -> entityToDTO(d)).collect(Collectors.toList());
    }

    public Department DTOToEntity(DepartmentDTO dto) {
        Department department = new Department();
        department.setName(dto.getName());
        //department.setPersonList(personConverter.DTOToEntity(dto.getPersons()));
        return department;
    }

    public List<Department> DTOToEntity (List<DepartmentDTO> dto){
        return dto.stream().map(d -> DTOToEntity(d)).collect(Collectors.toList());
    }
}
