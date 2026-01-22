package com.tajwebapp.service;

import com.tajwebapp.exception.DepartmentNotFoundException;
import com.tajwebapp.model.Department;
import com.tajwebapp.repo.DepartmentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    DepartmentRepo repo;

    public ResponseEntity<List<Department>> getAllDepartments() {
        try{
            List<Department> departments = repo.findAll();
            return new ResponseEntity<>(departments, HttpStatus.OK);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Department> getDepartmentById(int id) {
        try{
            Optional<Department> department = repo.findById(id);
            if(department.isPresent()){
                return new ResponseEntity<>(department.get(), HttpStatus.OK);
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

    public ResponseEntity<Department> addDepartment(Department department) {
        try{
            Optional<Department> departmentFromDB = repo.save(department);
            System.out.println(departmentFromDB);
            return new ResponseEntity<>(departmentFromDB.get(), HttpStatus.CREATED);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<Department> updateDepartment(Department department) {
        try{
            Optional<Department> updatedDepartment = repo.save(department);
            return new ResponseEntity<>(updatedDepartment.get(), HttpStatus.CREATED);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<String> deleteDepartment(int id) {
        try{
            Optional<Department> department = repo.findById(id);
            if(department.isPresent()){
                repo.remove(id);
                return new ResponseEntity<>("Department deleted", HttpStatus.OK);
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
