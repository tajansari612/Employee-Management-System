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

    public List<Department> getAllDepartments() {
        try{
            return repo.findAll();
        }catch (Exception e) {
            System.out.println("api: getAllDepartments :Internal server error :" + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public Department getDepartmentById(int id) {
        try{
            Optional<Department> department = repo.findById(id);
            if(department.isPresent()){
                return department.get();
            }else{
                throw new DepartmentNotFoundException(id);
            }
        }catch (DepartmentNotFoundException ex) {
            System.out.println("error: "+ ex.getMessage());
            throw ex;
        } catch (Exception e) {
            System.out.println("api: getDepartmentById :Internal server error :" + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public Department getDepartmentByName(String name) {
        try{
            Optional<Department> department = repo.findByName(name);
            if(department.isPresent()){
                return department.get();
            }
            throw new DepartmentNotFoundException(name);
        } catch (DepartmentNotFoundException ex) {
            System.out.println("error: " + ex.getMessage());
            throw ex;
        } catch (Exception e) {
            System.out.println("api: getDepartmentByName :Internal server error :" + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public Department addDepartment(Department department) {
        try{
            if(repo.findByName(department.getName()).isPresent()){
                throw new DepartmentAlreadyExistsException(department.getName());
            }
            return repo.save(department).get();
        } catch (DepartmentAlreadyExistsException ex) {
            System.out.println("error: " + ex.getMessage());
            throw ex;
        }catch (Exception e) {
            System.out.println("api: addDepartment :Internal server error :" + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public Department updateDepartment(Department department) {
        try{
            if(repo.findById(department.getId()).isPresent()){
                return repo.save(department).get();
            }
            throw new DepartmentNotFoundException(department.getId());
        } catch (DepartmentNotFoundException ex) {
            System.out.println("error: " + ex.getMessage());
            throw ex;
        }catch (Exception e) {
            System.out.println("api: updateDepartment :Internal server error :" + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    public String deleteDepartment(int id) {
        try{
            Optional<Department> department = repo.findById(id);
            if(department.isPresent()){
                repo.remove(id);
                return "Department deleted";
            }else{
                throw new DepartmentNotFoundException(id);
            }
        } catch (DepartmentNotFoundException ex) {
            System.out.println("error: "+ ex.getMessage());
            throw ex;
        } catch (Exception e) {
            System.out.println("api: deleteDepartment :Internal server error :" + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }
}
