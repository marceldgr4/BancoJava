package com.Banco.controller;

import com.Banco.model.domain.Employee.Cashier;
import com.Banco.model.domain.Employee.Employee;
import com.Banco.model.domain.Employee.Receptionist;
import com.Banco.model.domain.Employee.Supervisor;

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
    public Employee registerCashier(int id, String fullName,double salary,int yearsWorked){
        Employee employee = new Cashier(id,fullName,salary,yearsWorked);
        employeeService.addEmployee(employee);
        return employee;
    }
    public Employee registerSupervisor(int id, String fullName,double salary,int yearsWorked){
        Employee employee = new Supervisor(id,fullName,salary,yearsWorked);
        employeeService.addEmployee(employee);
        return employee;
    }
    public  Employee registerReceptionist(int id, String fullName,double salary,int yearsWorked){
        Employee employee = new Receptionist(id,fullName,salary,yearsWorked);
        employeeService.addEmployee(employee);
        return employee;
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
