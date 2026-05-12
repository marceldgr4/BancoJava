package com.Banco.service;

import com.Banco.model.domain.Employee.Employee;
import com.Banco.repository.EmployeeRepository;

import java.util.List;
import java.util.Optional;

public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        if (employeeRepository == null)
            throw new IllegalArgumentException("EmployeeRepositori must not  be null");
        this.employeeRepository = employeeRepository;
        }
        public void addEmployee(Employee employee){
        if (employee == null)
            throw new IllegalArgumentException("Employee must  not  be null");
        if (employeeRepository.existsById(employee.getId()))
            throw new IllegalArgumentException("Employee winth ID'"+employee.getId()+ "'already exists");
        employeeRepository.Save(employee);
    }
    public  Optional<Employee> findEmploteeById(int id){
        return employeeRepository.FindById(id);
    }
    public Employee getEmployeeById(int id){
        return employeeRepository.FindById(id)
                .orElseThrow(()->new IllegalArgumentException("Employee not found with id"+id));
    }

    public List<Employee> getAllEmployees(){
        return employeeRepository.FindAll();
    }

    public int getVacationDay(int employeeId){
        return getEmployeeById(employeeId).calculateVacationDays();
    }
    public int getTotalEmployees(){
        return employeeRepository.count();
    }
}
