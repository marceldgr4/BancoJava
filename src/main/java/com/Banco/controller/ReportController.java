package com.Banco.controller;

import com.Banco.model.domain.Account.BankAccount;
import com.Banco.model.domain.Employee.Employee;
import com.Banco.model.domain.Person.Client;
import com.Banco.service.AccountService;
import com.Banco.service.BankService;
import com.Banco.service.ClientService;
import com.Banco.service.EmployeeService;

import java.util.List;

public class ReportController {

    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final AccountService accountService;

    public ReportController(ClientService clientService,
                            EmployeeService employeeService,
                            AccountService accountService) {
        this.clientService   = clientService;
        this.employeeService = employeeService;
        this.accountService  = accountService;
    }



    public String generateClientsReport() {
        List<Client> clients = clientService.getAllClients();
        StringBuilder sb = new StringBuilder();
        sb.append("══════════ CLIENTS REPORT ══════════\n");
        sb.append("Total registered: ").append(clients.size()).append("\n\n");
        clients.forEach(c -> {
            sb.append(c).append("\n");
            c.getAccounts().forEach(a ->
                    sb.append("  └─ ").append(a).append("\n")
            );
        });
        return sb.toString();
    }

    public String generateEmployeesReport() {
        List<Employee> employees = employeeService.getAllEmployees();
        StringBuilder sb = new StringBuilder();
        sb.append("══════════ EMPLOYEES REPORT ══════════\n");
        sb.append("Total registered: ").append(employees.size()).append("\n\n");
        employees.forEach(e ->
                sb.append(e).append("\n")
        );
        return sb.toString();
    }

    public String generateAccountsReport() {
        List<BankAccount> accounts = accountService.getAllAccounts();
        StringBuilder sb = new StringBuilder();
        sb.append("══════════ ACCOUNTS REPORT ══════════\n");
        sb.append("Total accounts: ").append(accounts.size()).append("\n");
        sb.append("Total class count: ").append(BankAccount.getAccountCount()).append("\n\n");
        accounts.forEach(a -> {
            sb.append(a).append("\n");
            a.getTransactions().forEach(t ->
                    sb.append("  └─ ").append(t).append("\n")
            );
        });
        return sb.toString();
    }

    public String generateSystemSummary() {
        return String.format(
                "══════════ SYSTEM SUMMARY ══════════\n" +
                        "  Clients:   %d\n" +
                        "  Employees: %d\n" +
                        "  Accounts:  %d\n",
                clientService.getTotalClients(),
                employeeService.getTotalEmployees(),
                accountService.getAllAccounts().size()
        );
    }
}
