package com.Banco.controller;

import com.Banco.model.Investment.InvestmentCompany;
import com.Banco.service.BankService;

import java.util.List;

public class BankController {

    private final BankService bankService;             // kept for cross-cutting queries
    private final ClientController      clientController;
    private final EmployeeController    employeeController;
    private final AccountController     accountController;
    private final TransactionController transactionController;
    private final ReportController      reportController;

    public BankController(BankService bankService) {
        if (bankService == null)
            throw new IllegalArgumentException("BankService cannot be null.");

        this.bankService         = bankService;
        this.clientController      = new ClientController(bankService.clients());
        this.employeeController    = new EmployeeController(bankService.employees());
        this.accountController     = new AccountController(bankService.accounts());
        this.transactionController = new TransactionController(bankService.transactions());
        this.reportController      = new ReportController(bankService.reports());
    }

    // ── Sub-controllers ────────────────────────────────────────────────────────
    public ClientController      clients()      { return clientController; }
    public EmployeeController    employees()    { return employeeController; }
    public AccountController     accounts()     { return accountController; }
    public TransactionController transactions() { return transactionController; }
    public ReportController      reports()      { return reportController; }

    // ── Cross-cutting: companies (no dedicated controller needed at this scale) ─
    /**
     * Returns all registered investment companies.
     * Used by the view when creating an InvestmentAccount.
     */
    public List<InvestmentCompany> getAllCompanies() {
        return bankService.getAllCompanies();
    }
}