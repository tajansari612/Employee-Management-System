package com.tajwebapp.exception;

public class EmployeeAlreadyExistsException extends RuntimeException{
    public EmployeeAlreadyExistsException(String email){
        super("Employee already exists with email: "+ email);
    }
}
