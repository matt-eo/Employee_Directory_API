package com.matteo88.employeedirectoryapi.dao;

import com.matteo88.employeedirectoryapi.model.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EmployeeRepository extends CrudRepository<Employee, Integer> {

    List<Employee> findAllByOrderByLastNameAsc();
}
