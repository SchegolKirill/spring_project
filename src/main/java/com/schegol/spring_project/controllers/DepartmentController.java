package com.schegol.spring_project.controllers;

import com.schegol.spring_project.converters.DepartmentConverter;
import com.schegol.spring_project.repositories.dto.DepartmentDTO;
import com.schegol.spring_project.services.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;
    private final DepartmentConverter departmentConverter;

    @GetMapping("/getall")
    public List<DepartmentDTO> getAllDepartments() {
        return departmentConverter.entityToDTO(departmentService.getAllDepartments());
    }

    @PostMapping("/createdepartment")
    public String createDepartment(@RequestBody DepartmentDTO dto) {
        departmentService.createNewDepartment(departmentConverter.DTOToEntity(dto));
        return "New department successfully created";
    }

    @PutMapping("/addptd")
    public String addPersonToDepartment(@RequestParam("personId") Integer personId,
                                        @RequestParam("departmentId") Integer departmentId){
        departmentService.addPersonToDepartment(personId, departmentId);
        return "Person with ID = " + personId + " successfully added to department with ID = " + departmentId;
    }

    @PutMapping("/removepfd")
    public String removePersonFromDepartment(@RequestParam("personId") Integer personId,
                                        @RequestParam("departmentId") Integer departmentId){
        departmentService.removePersonFromDepartment(personId, departmentId);
        return "Person with ID = " + personId + " successfully removed from department with ID = " + departmentId;
    }

    @DeleteMapping("/deletedep/{id}")
    public String deleteDepartment(@PathVariable Integer id) {
        departmentService.deleteDepartment(id);
        return "Department with ID = " + id + " successfully removed";
    }
}
