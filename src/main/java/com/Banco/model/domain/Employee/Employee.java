package com.Banco.model.domain.Employee;

import com.Banco.model.Emun.EmployeeType;
import com.Banco.model.domain.Person.Person;

public abstract class Employee extends Person {

    private static int employeeCount = 0;

    private double salary;
    private int yearsWorked;

    private static final int VACATION_BASE_DAYS = 5;
    private static final int VACATION_INCREMENT = 2;
    private static final int VACATION_MAX_DAYS = 20;

    protected Employee(int id, String fullName, double salary, int yearsWorked) {
        super(id, fullName);
        if (salary < 0)
            throw new IllegalArgumentException("Salary cannot be negative");
        if (yearsWorked < 0)
            throw new IllegalArgumentException("Years worked cannot be negative");
        this.salary = salary;
        this.yearsWorked = yearsWorked;
        employeeCount++;
    }

    public abstract EmployeeType getEmployeeType();

    public int calculateVacationDays() {
        if (yearsWorked <= 0) return 0;
        if (yearsWorked == 1) return VACATION_BASE_DAYS;
        int days = VACATION_BASE_DAYS + (yearsWorked - 1) * VACATION_INCREMENT;
        return Math.min(days, VACATION_MAX_DAYS);
    }

    public String getPosition() {
        return getEmployeeType().name();
    }

    public static int getEmployeeCount() {
        return employeeCount;
    }

    static void resetEmployeeCount() {
        employeeCount = 0;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        if (salary < 0)
            throw new IllegalArgumentException("Salary cannot be negative");
        this.salary = salary;
    }

    public int getYearsWorked() {
        return yearsWorked;
    }

    public void setYearsWorked(int yearsWorked) {
        if (yearsWorked < 0)
            throw new IllegalArgumentException("Years worked cannot be negative");
        this.yearsWorked = yearsWorked;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s, name=%s, salary=$%.2f, years=%d, vacationDays=%d]",
                getEmployeeType(), getId(), getFullName(), salary, yearsWorked, calculateVacationDays());
    }
}




