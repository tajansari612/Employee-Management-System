package com.tajwebapp.service;

import com.tajwebapp.exception.EmployeeNotFoundException;
import com.tajwebapp.model.Employee;
import com.tajwebapp.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo;

    public List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        try {
            employees = repo.findAll();
            return employees;
        } catch (Exception e) {
            System.out.println("error: Unknown Exception");
            throw e;
        }
    }

    public Employee getEmployeeById(int id) {
        try {
            Optional<Employee> employee = repo.findById(id);
            if(employee.isPresent()){
                return employee.get();
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

    public Employee addEmployee(Employee employee) {
        try {
            Optional<Employee> employeeFromDB = repo.save(employee);
            return employeeFromDB.get();
        } catch (Exception e) {
            System.out.println("error: Unknown Exception");
            throw e;
        }
    }

    public Employee updateEmployee(Employee employee) {
        try {
            Optional<Employee> updatedEmployee = repo.save(employee);
            return updatedEmployee.get();
        } catch (Exception e) {
            System.out.println("error: Unknown Exception");
            throw e;
        }
    }

    public String deleteEmployee(int id) {
        try{
            Optional<Employee> employee = repo.findById(id);
            if(employee.isPresent()){
                repo.remove(id);
                return "Employee Deleted";
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
}
