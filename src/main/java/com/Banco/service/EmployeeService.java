package com.Banco.service;

import com.Banco.model.domain.Employee.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        if(employee == null) throw new IllegalArgumentException("Employee must not be null");
        if(findEmployeeById(employee.getId()).isPresent())
            throw new IllegalArgumentException("Employee with ID:"+employee.getId()+"already exists");
        employees.add(employee);
    }
    public Optional<Employee> findEmployeeById(int id) {
        return employees.stream().filter(employee -> employee.getId() == id).findFirst();

    }
    public List<Employee> findAllEmployees() {
        return Collections.unmodifiableList(employees);
    }
}
