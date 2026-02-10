package com.Employee.Exception_handler;

public class EmployeeNotFoundException  extends RuntimeException {
    
    public EmployeeNotFoundException(String message) {
        super("Employee not found with id: " + message);
    }
    public EmployeeNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    public EmployeeNotFoundException(Throwable cause) {
        super(cause);
    }

}
