package com.tajwebapp.controller;

import com.tajwebapp.model.Department;
import com.tajwebapp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Autowired
    DepartmentService departmentService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Department>> getAllDepartments(){
        return departmentService.getAllDepartments();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") int id){
        return departmentService.getDepartmentById(id);
    }

    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department){
        return departmentService.addDepartment(department);
    }

    @PutMapping("/update")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department){
        return departmentService.updateDepartment(department);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") int id){
        return departmentService.deleteDepartment(id);
    }
}
