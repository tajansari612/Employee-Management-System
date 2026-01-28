package com.tajwebapp.controller;

import com.tajwebapp.model.Employee;
import com.tajwebapp.service.EmployeeService;
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
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/getAll")
    public ResponseEntity<List<Employee>> getAllEmployees() throws Exception {
        return new ResponseEntity<>(
                employeeService.getAllEmployees(),
                HttpStatus.OK
        );
    }

    @GetMapping("/get/id/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") int id){
        return new ResponseEntity<>(
                employeeService.getEmployeeById(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/get/email/{email}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable("email") String email){
        return new ResponseEntity<>(
                employeeService.getEmployeeByEmail(email),
                HttpStatus.OK
        );
    }

    @PostMapping("/add")
    public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(
        employeeService.addEmployee(employee),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/update")
    public ResponseEntity<Employee> updateEmployee(@RequestBody Employee employee){
        return new ResponseEntity<>(
                employeeService.updateEmployee(employee),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") int id){
        return new ResponseEntity<>(
                employeeService.deleteEmployee(id),
                HttpStatus.OK
        );
    }
}
