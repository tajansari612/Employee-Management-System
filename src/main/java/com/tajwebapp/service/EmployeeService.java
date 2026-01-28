package com.tajwebapp.service;

import com.tajwebapp.exception.EmployeeAlreadyExistsException;
import com.tajwebapp.exception.EmployeeNotFoundException;
import com.tajwebapp.model.Employee;
import com.tajwebapp.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepo repo;

    public List<Employee> getAllEmployees() throws Exception {
        return repo.findAll();
    }

    public Employee getEmployeeById(int id){
        Optional<Employee> employee = repo.findById(id);
        if(employee.isPresent()){
            return employee.get();
        }
        throw new EmployeeNotFoundException(id);
    }

    public Employee getEmployeeByEmail(String email) throws EmployeeNotFoundException {
        Optional<Employee> employee = repo.findByEmail(email);
        if(employee.isPresent()){
            return employee.get();
        }
        throw new EmployeeNotFoundException(email);
    }

    public Employee addEmployee(Employee employee) throws EmployeeAlreadyExistsException{
        if(repo.findByEmail(employee.getEmail()).isPresent()){
            System.out.println(repo.findByEmail(employee.getEmail()));
            throw new EmployeeAlreadyExistsException(employee.getEmail());
        }
        return repo.save(employee).get();
    }

    public Employee updateEmployee(Employee employee) throws EmployeeNotFoundException{
        if(repo.findById(employee.getId()).isPresent()){
            Optional<Employee> updatedEmployee = repo.save(employee);
            return updatedEmployee.get();
        }
        throw new EmployeeNotFoundException(employee.getId());
    }

    public String deleteEmployee(int id) {
        Optional<Employee> employee = repo.findById(id);
        if(employee.isPresent()){
            repo.remove(id);
            return "Employee Deleted";
        }
        throw new EmployeeNotFoundException(id);
    }
}
