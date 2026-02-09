package com.Employee.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
