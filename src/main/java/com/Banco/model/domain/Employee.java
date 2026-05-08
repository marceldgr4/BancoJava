package com.Banco.model.domain;

public class Employee extends Clients{
    private int yearWorked;
    private String workStation;
    private  double salary;

    public Employee(String userNumber, String fullName, int yearWorked, String workStation, double salary) {
        super(userNumber, fullName);
        this.yearWorked = yearWorked;
        this.workStation = workStation;
        this.salary = salary;
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
