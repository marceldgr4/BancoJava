package com.Banco.model.domain.Employee;

import com.Banco.model.type.EmployeeType;

public class Receptionist extends Employee {
    public Receptionist(int id, String fullname, double salary, int yearsWorked) {
        super(id,fullname,salary,yearsWorked);
    }
    @Override
    public EmployeeType getEmployeeType() {
        return EmployeeType.RECEPCIONISTA;
    }
}
