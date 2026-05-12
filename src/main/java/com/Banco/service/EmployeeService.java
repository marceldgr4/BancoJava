package com.Banco.service;

import com.Banco.exceptions.DuplicateResourceException;
import com.Banco.exceptions.EmployeeNotFoundException;
import com.Banco.model.domain.Employee.Employee;
import com.Banco.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        if (employeeRepository == null)
            throw new IllegalArgumentException("EmployeeRepository must not be null");
        this.employeeRepository = employeeRepository;
        }
        public void addEmployee(Employee employee){
        if (employee == null)
            throw new IllegalArgumentException("Employee must  not  be null");
        if (employeeRepository.existsById(employee.getId()))
            throw new DuplicateResourceException("Employee with ID '" + employee.getId() + "' already exists");
        employeeRepository.save(employee);
    }
    public Optional<Employee> findEmployeeById(int id){
        return employeeRepository.findById(id);
    }
    public Employee getEmployeeById(int id){
        return employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.findAll();
    }

    public int getVacationDay(int employeeId){
        return getEmployeeById(employeeId).calculateVacationDays();
    }
    public int getTotalEmployees(){
        return employeeRepository.count();
    }
}
