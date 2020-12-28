package com.matteo88.employeedirectoryapi.service;

import com.matteo88.employeedirectoryapi.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee findById(int id);

    void save(Employee employee);

    void deleteById(int id);
}
