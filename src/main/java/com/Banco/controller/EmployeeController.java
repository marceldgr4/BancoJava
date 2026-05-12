package com.Banco.controller;

import com.Banco.model.domain.Employee.Employee;
import com.Banco.repository.EmployeeRepository;
import com.Banco.service.EmployeeService;

import java.util.List;

public class EmployeeController {
    private final EmployeeService employeeService;
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    public void registerEmployee(Employee employee){
        employeeService.addEmployee(employee);
    }
    public Employee getEmployeeById(int id){
        return employeeService.getEmployeeById(id);
    }
    public List<Employee> listAllEmployees(){
        return employeeService.getAllEmployees();
    }
    public int getVacationDays(int employeeId){
        return employeeService.getVacationDay(employeeId);
    }
}
