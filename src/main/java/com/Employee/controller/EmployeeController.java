package com.employee.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.model.Employee;
import com.employee.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employees")
public class EmployeeController {
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PostMapping
    public ResponseEntity<Employee> createEmployee(@Valid @RequestBody Employee employee) {
        logger.info("POST /employees - Creating employee: {}", employee.getName());
        Employee createdEmployee = employeeService.createEmployee(employee);
        logger.info("Employee created with id: {}", createdEmployee.getId());
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }

    @GetMapping
    public ResponseEntity<List<Employee>> getEmployees() {
        logger.info("GET /employees - Fetching all employees");
        List<Employee> employees = employeeService.getEmployees();
        logger.info("Retrieved {} employees", employees.size());
        return ResponseEntity.ok(employees);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        logger.info("GET /employees/{} - Fetching employee", id);
        Employee employee = employeeService.getEmployeeById(id);
        logger.info("Employee found: {}", employee.getName());
        return ResponseEntity.ok(employee);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(
            @PathVariable Long id, 
            @Valid @RequestBody Employee employee) {
        logger.info("PUT /employees/{} - Updating employee", id);
        Employee updatedEmployee = employeeService.updateEmployee(id, employee);
        logger.info("Employee updated: {}", updatedEmployee.getName());
        return ResponseEntity.ok(updatedEmployee);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
        logger.info("DELETE /employees/{} - Deleting employee", id);
        employeeService.deleteEmployeeById(id);
        logger.info("Employee deleted with id: {}", id);
        return ResponseEntity.noContent().build();
    }
}
