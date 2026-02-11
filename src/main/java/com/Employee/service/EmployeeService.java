package com.employee.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.employee.Exception_handler.EmployeeNotFoundException;
import com.employee.model.Employee;
import com.employee.model.EmployeeRepository;

@Service
public class EmployeeService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        logger.debug("Saving employee: {}", employee.getName());
        Employee savedEmployee = employeeRepository.save(employee);
        logger.debug("Employee saved with id: {}", savedEmployee.getId());
        return savedEmployee;
    }

    public void deleteEmployeeById(Long id) {
        logger.debug("Attempting to delete employee with id: {}", id);
        
        // Validate existence before deleting
        if (!employeeRepository.existsById(id)) {
            logger.warn("Cannot delete - Employee not found with id: {}", id);
            throw new EmployeeNotFoundException(String.valueOf(id));
        }
        
        employeeRepository.deleteById(id);
        logger.debug("Employee deleted with id: {}", id);
    }

    public List<Employee> getEmployees() {
        logger.debug("Fetching all employees");
        List<Employee> employees = employeeRepository.findAll();
        logger.debug("Found {} employees", employees.size());
        return employees;
    }

    public Employee getEmployeeById(Long id) {
        logger.debug("Fetching employee with id: {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Employee not found with id: {}", id);
                    return new EmployeeNotFoundException(String.valueOf(id));
                });
    }

    public Employee updateEmployee(Long id, Employee employeeDetails) {
        logger.debug("Attempting to update employee with id: {}", id);
        
        // Fetch existing employee (throws exception if not found)
        Employee existingEmployee = getEmployeeById(id);
        
        // Update fields
        existingEmployee.setName(employeeDetails.getName());
        existingEmployee.setSalary(employeeDetails.getSalary());
        existingEmployee.setAge(employeeDetails.getAge());
        
        // Save and return
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        logger.debug("Employee updated with id: {}", updatedEmployee.getId());
        return updatedEmployee;
    }
}
