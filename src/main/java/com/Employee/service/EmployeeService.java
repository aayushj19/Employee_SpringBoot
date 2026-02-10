package com.Employee.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.Employee.model.Employee;
import com.Employee.repository.EmployeeRepository;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }
    public void deleteEmployeeById(Long id) {
        employeeRepository.deleteById(id);
    }
    public List<Employee> getEmployees() {
        return employeeRepository.findAll();
    }
    public Optional<Employee> getEmployeeById(Long id) {
        return employeeRepository.findById(id);
    }
    public Employee updateEmployee(Long id, Employee employee) {
        Employee existingEmployee = getEmployeeById(id).orElse(null);
        if (existingEmployee != null) {
            existingEmployee.setName(employee.getName());
            existingEmployee.setSalary(employee.getSalary());
            existingEmployee.setAge(employee.getAge());
            return employeeRepository.save(existingEmployee);
        }
        return null;
    }
}
