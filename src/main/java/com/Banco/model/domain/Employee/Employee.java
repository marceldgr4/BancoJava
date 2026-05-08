package com.Banco.model.domain.Employee;

import com.Banco.model.domain.Person.Client;

public class Employee extends Client {
    private int id;
    private int yearWorked;
    private String workStation;
    private  double salary;

    public Employee(int id,String userNumber, String fullName, int yearWorked, String workStation, double salary) {
        super(userNumber, fullName);
        this.id = id;
        this.yearWorked = yearWorked;
        this.workStation = workStation;
        this.salary = salary;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYearWorked() {
        return yearWorked;
    }

    public void setYearWorked(int yearWorked) {
        this.yearWorked = yearWorked;
    }

    public String getWorkStation() {
        return workStation;
    }

    public void setWorkStation(String workStation) {
        this.workStation = workStation;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }
}
