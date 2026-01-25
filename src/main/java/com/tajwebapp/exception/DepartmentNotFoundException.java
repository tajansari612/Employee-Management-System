package com.tajwebapp.exception;

public class DepartmentNotFoundException extends RuntimeException{
    public DepartmentNotFoundException(int id){
        super("Department not found with id: " + id);
    }
    public DepartmentNotFoundException(String name){
        super("Department not found with name: " + name);
    }
}
