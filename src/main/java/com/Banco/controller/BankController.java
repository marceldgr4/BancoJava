package com.Banco.controller;

import com.Banco.service.BankService;

public class BankController {
    private final ClientController clientController;
    private final EmployeeController employeeController;
    private final AccountController accountController;
    private final TransactionController transactionController;
    private final ReportController reportController;

    public BankController(BankService bankService) {
        if (bankService == null) {
            throw new IllegalArgumentException("Bank service cannot be null.");
        }

        this.clientController = new ClientController(
                bankService.clients()
        );
        this.employeeController = new EmployeeController(
                bankService.employees()
        );

        this.accountController = new AccountController(
                bankService.accounts()
        );
        this.transactionController = new TransactionController(
                bankService.transactions()
        );
        this.reportController = new ReportController(
                bankService.reports()
        );
    }
        public ClientController      clients()      { return clientController; }
        public EmployeeController    employees()    { return employeeController; }
        public AccountController     accounts()     { return accountController; }
        public TransactionController transactions() { return transactionController; }
        public ReportController      reports()      { return reportController; }


}
