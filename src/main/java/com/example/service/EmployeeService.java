package com.example.service;

import java.util.List;

import com.example.model.Employee;

public interface EmployeeService {
    Employee save(Employee employee);

    List<Employee> findAll();
}
