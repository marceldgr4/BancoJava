package com.Banco.util.validator;

public final class EmployeeValidator extends BaseValidator{
 private EmployeeValidator(){

 }
 public static void validateSalary(double salary){
     requirePositive(salary, "salary");
 }
 public static void validateYearsWorked(int years){
     requireNonNegative(years, "years worked");
 }
    public static void validateEmployeeData(int id, String fullName, double salary, int yearsWorked) {
        requirePositive(id, "Employee ID");
        requireNonBlank(fullName, "Full name");
        validateSalary(salary);
        validateYearsWorked(yearsWorked);
    }
}

