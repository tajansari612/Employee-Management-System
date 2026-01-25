package com.tajwebapp.exception;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(int id){
        super("Employee not found with id: " + id);
    }
    public EmployeeNotFoundException(String email){
        super("Employee not found with email: " + email);
    }
}
