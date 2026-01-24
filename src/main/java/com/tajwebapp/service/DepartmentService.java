package com.tajwebapp.service;

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
            System.out.println("error: Unknown Exception");
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
            System.out.println("error: Unknow Exception :" + e.getMessage());
            throw e;
        }
    }

    public Department addDepartment(Department department) {
        try{
            Optional<Department> departmentFromDB = repo.save(department);
            System.out.println(departmentFromDB);
            return departmentFromDB.get();
        }catch (Exception e) {
            System.out.println("error: Unknown Exception");
            throw e;
        }
    }

    public Department updateDepartment(Department department) {
        try{
            Optional<Department> updatedDepartment = repo.save(department);
            return updatedDepartment.get();
        }catch (Exception e) {
            System.out.println("error: Unknown Exception");
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
            System.out.println("error: Unknown Exception" + e.getMessage());
            throw e;
        }
    }
}
