package com.Banco.model.domain.Employee;

import com.Banco.model.type.EmployeeType;

public class Cashier extends Employee {
    public Cashier(int id, String fullName, double salary,
                   int yearsWorked) {
        super(id, fullName, salary, yearsWorked);
    }
    @Override
    public EmployeeType getEmployeeType() {
        return EmployeeType.CASHIER;
    }
}
