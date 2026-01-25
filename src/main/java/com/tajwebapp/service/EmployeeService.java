package com.tajwebapp.service;

import com.tajwebapp.exception.EmployeeAlreadyExistsException;
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
            System.out.println("api: getAllEmployees :Internal server error :" + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public Employee getEmployeeById(int id) {
        try {
            Optional<Employee> employee = repo.findById(id);
            if(employee.isPresent()){
                return employee.get();
            }
            throw new EmployeeNotFoundException(id);
        } catch (EmployeeNotFoundException ex) {
            System.out.println("error: "+ ex.getMessage());
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("api: getEmployeeById :Internal server error :" + e.getMessage());
            throw e;
        }
    }

    public Employee getEmployeeByEmail(String email) {
        try {
            Optional<Employee> employee = repo.findByEmail(email);
            if(employee.isPresent()){
                return employee.get();
            }
            throw new EmployeeNotFoundException(email);
        } catch (EmployeeNotFoundException ex) {
            System.out.println("error: "+ ex.getMessage());
            throw ex;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("api: getEmployeeByEmail :Internal server error :" + e.getMessage());
            throw e;
        }
    }

    public Employee addEmployee(Employee employee) {
        try {
            if(repo.findByEmail(employee.getEmail()).isPresent()){
                System.out.println(repo.findByEmail(employee.getEmail()));
                throw new EmployeeAlreadyExistsException(employee.getEmail());
            }
            return repo.save(employee).get();
        } catch (EmployeeAlreadyExistsException ex) {
            System.out.println("error: "+ ex.getMessage());
            throw new EmployeeAlreadyExistsException(employee.getEmail());
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("api: addEmployee :Internal server error :" + e.getMessage());
            throw e;
        }
    }

    public Employee updateEmployee(Employee employee) {
        try {
            if(repo.findById(employee.getId()).isPresent()){
                Optional<Employee> updatedEmployee = repo.save(employee);
                return updatedEmployee.get();
            }
            throw new EmployeeNotFoundException(employee.getId());
        } catch (EmployeeNotFoundException ex) {
            System.out.println("error: "+ ex.getMessage());
            throw new EmployeeNotFoundException(employee.getId());
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("api: updateEmployee :Internal server error :" + e.getMessage());
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
            System.out.println("api: deleteEmployee :Internal server error :" + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
