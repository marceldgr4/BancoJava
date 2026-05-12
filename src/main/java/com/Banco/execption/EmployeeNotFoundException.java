package com.Banco.execption;

public class EmployeeNotFoundException extends RuntimeException{
    public EmployeeNotFoundException(int employeeId){
        super("Employee not found with ID:"+ employeeId);
    }
}
