package com.Banco.model.domain.Employee;

import com.Banco.model.type.EmployeeType;

public class Supervisor extends Employee {
    public Supervisor(int id, String fullname, double salary, int yearsWorked) {
        super(id,fullname,salary,yearsWorked);
    }
    @Override
    public EmployeeType getEmployeeType() {
        return EmployeeType.SUPERVISOR;
    }
}
