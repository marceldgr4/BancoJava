package com.Banco.model.domain.Account;

public class SavingsAccount extends Account {
    private double AnnualSavingsPercentage;
    private double MonthlySavingsPercentage;

    public SavingsAccount(String accountNumber, Client owner, double Beginningbalance, double annualSavingsPercentage, double monthlySavingsPercentage) {
        super(accountNumber, owner, Beginningbalance);
        AnnualSavingsPercentage = annualSavingsPercentage;
        MonthlySavingsPercentage = monthlySavingsPercentage;
    }

    public double getAnnualSavingsPercentage() {
        return AnnualSavingsPercentage;
    }

    public void setAnnualSavingsPercentage(double annualSavingsPercentage) {
        AnnualSavingsPercentage = annualSavingsPercentage;
    }

    public double getMonthlySavingsPercentage() {
        return MonthlySavingsPercentage;
    }

    public void setMonthlySavingsPercentage(double monthlySavingsPercentage) {
        MonthlySavingsPercentage = monthlySavingsPercentage;
    }
}
