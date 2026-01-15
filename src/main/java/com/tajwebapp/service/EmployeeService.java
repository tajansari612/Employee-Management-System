package com.tajwebapp.service;

import com.tajwebapp.model.Employee;
import com.tajwebapp.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo;

    public ResponseEntity<Employee> addEmployee(Employee employee) {
        try {
            return new ResponseEntity<>(
                    repo.save(employee),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Employee> updateEmployee() {
        return null;
    }

    public ResponseEntity<Employee> deleteEmployee(int id) {
        return null;
    }

    public ResponseEntity<List<Employee>> getAllEmployees() {
        return null;
    }

    public ResponseEntity<Employee> getEmployeeById(int id) {
        return null;
    }
}
