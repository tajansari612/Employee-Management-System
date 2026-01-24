package com.tajwebapp.controller;

import com.tajwebapp.model.Department;
import com.tajwebapp.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
        return new ResponseEntity<>(
                departmentService.getAllDepartments(),
                HttpStatus.OK
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable("id") int id){
        return new ResponseEntity<>(
                departmentService.getDepartmentById(id),
                HttpStatus.OK
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Department> addDepartment(@RequestBody Department department){
        return new ResponseEntity<>(
                departmentService.addDepartment(department),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Department> updateDepartment(@RequestBody Department department){
        return new ResponseEntity<>(
                departmentService.updateDepartment(department),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDepartment(@PathVariable("id") int id){
        return new ResponseEntity<>(
                departmentService.deleteDepartment(id),
                HttpStatus.OK
        );
    }
}
