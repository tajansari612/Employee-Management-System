package com.tajwebapp.service;

import com.tajwebapp.exception.DepartmentAlreadyExistsException;
import com.tajwebapp.exception.DepartmentNotFoundException;
import com.tajwebapp.model.Department;
import com.tajwebapp.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo repo;

    public List<Department> getAllDepartments() throws Exception{
        return repo.findAll();
    }

    public Department getDepartmentById(int id) throws DepartmentNotFoundException{
        Optional<Department> department = repo.findById(id);
        if(department.isPresent()){
            return department.get();
        }
        throw new DepartmentNotFoundException(id);
    }

    public Department getDepartmentByName(String name) {
        Optional<Department> department = repo.findByName(name);
        if(department.isPresent()){
            return department.get();
        }
        throw new DepartmentNotFoundException(name);
    }

    public Department addDepartment(Department department) throws DepartmentAlreadyExistsException{
        if(repo.findByName(department.getName()).isPresent()){
            throw new DepartmentAlreadyExistsException(department.getName());
        }
        return repo.save(department).get();
    }

    public Department updateDepartment(Department department) throws DepartmentNotFoundException{
        if(repo.findById(department.getId()).isPresent()){
            return repo.save(department).get();
        }
        throw new DepartmentNotFoundException(department.getId());
    }

    public String deleteDepartment(int id) throws DepartmentNotFoundException{
        Optional<Department> department = repo.findById(id);
        if(department.isPresent()){
            repo.remove(id);
            return "Department deleted";
        }
        throw new DepartmentNotFoundException(id);
    }
}
