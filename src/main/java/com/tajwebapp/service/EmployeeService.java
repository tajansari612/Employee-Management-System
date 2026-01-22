package com.tajwebapp.service;

import com.tajwebapp.exception.EmployeeNotFoundException;
import com.tajwebapp.model.Employee;
import com.tajwebapp.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo;

    public ResponseEntity<Employee> addEmployee(Employee employee) {
        try {
            Optional<Employee> employeeFromDB = repo.save(employee);
            return new ResponseEntity<>(
                    employeeFromDB.get(),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Employee> updateEmployee(Employee employee) {
        try {
            Optional<Employee> updatedEmployee = repo.save(employee);
            return new ResponseEntity<>(
                    updatedEmployee.get(),
                    HttpStatus.CREATED
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> deleteEmployee(int id) {
        try{
            Optional<Employee> employee = repo.findById(id);
            if(employee.isPresent()){
                repo.remove(id);
                return new ResponseEntity<>(
                        "Employee Deleted",
                        HttpStatus.OK
                );
            }else{
                throw new EmployeeNotFoundException(id);
            }
        } catch (EmployeeNotFoundException ex) {
            System.out.println("error: "+ ex.getMessage());
            throw ex;
        } catch (Exception e) {
            System.out.println("error: Unknown Exception");
            throw e;
        }
    }

    public ResponseEntity<List<Employee>> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            employees = repo.findAll();
            return new ResponseEntity<>(employees, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Employee> getEmployeeById(int id) {
        try {
            Optional<Employee> employee = repo.findById(id);
            if(employee.isPresent()){
                return new ResponseEntity<>(employee.get(), HttpStatus.OK);
            }else{
                throw new EmployeeNotFoundException(id);
            }
        } catch (EmployeeNotFoundException ex) {
            System.out.println("error: "+ ex.getMessage());
            throw ex;
        } catch (Exception e) {
            System.out.println("error: Unknow Exception");
            throw e;
        }
    }
}
