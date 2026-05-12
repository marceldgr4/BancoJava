package com.Banco.service;

import com.Banco.model.domain.Account.BankAccount;
import com.Banco.model.domain.Employee.Employee;
import com.Banco.model.domain.Person.Client;

import java.util.List;

public class ReportService {
    private final ClientService clientService;
    private final EmployeeService employeeService;
    private final AccountService accountService;
    private final com.Banco.repository.CompanyRepository companyRepository;

    public ReportService(ClientService clientService, EmployeeService employeeService, AccountService accountService, com.Banco.repository.CompanyRepository companyRepository) {
        this.clientService = clientService;
        this.employeeService = employeeService;
        this.accountService = accountService;
        this.companyRepository = companyRepository;
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

    // ── Table Data Methods ──────────────────────────────────────────────────

    public Object[][] getClientsTableData() {
        List<Client> clients = clientService.getAllClients();
        Object[][] data = new Object[clients.size()][3];
        for (int i = 0; i < clients.size(); i++) {
            Client c = clients.get(i);
            data[i][0] = c.getId();
            data[i][1] = c.getFullName();
            data[i][2] = c.getAccounts().size();
        }
        return data;
    }

    public Object[][] getEmployeesTableData() {
        List<Employee> employees = employeeService.getAllEmployees();
        Object[][] data = new Object[employees.size()][6];
        for (int i = 0; i < employees.size(); i++) {
            Employee e = employees.get(i);
            data[i][0] = e.getId();
            data[i][1] = e.getFullName();
            data[i][2] = e.getClass().getSimpleName();
            data[i][3] = String.format("$%.2f", e.getSalary());
            data[i][4] = e.getYearsWorked();
            data[i][5] = e.calculateVacationDays();
        }
        return data;
    }

    public Object[][] getAccountsTableData() {
        List<BankAccount> accounts = accountService.getAllAccounts();
        Object[][] data = new Object[accounts.size()][5];
        for (int i = 0; i < accounts.size(); i++) {
            BankAccount a = accounts.get(i);
            data[i][0] = a.getAccountNumber();
            data[i][1] = a.getOwner().getFullName();
            data[i][2] = a.getAccountType();
            data[i][3] = String.format("$%.2f", a.getBalance());
            
            String details = "N/A";
            if (a instanceof com.Banco.model.domain.Account.SavingsAccount sa) {
                details = String.format("Interest Rate: %.1f%%", sa.getAnnualInterestRate() * 100);
            } else if (a instanceof com.Banco.model.domain.Account.InvestmentAccount ia) {
                details = ia.getCompany() != null ? "Company: " + ia.getCompany().getName() : "Company: None";
            }
            data[i][4] = details;
        }
        return data;
    }

    public Object[][] getCompaniesTableData() {
        List<com.Banco.model.Investment.InvestmentCompany> companies = companyRepository.findAll();
        Object[][] data = new Object[companies.size()][5];
        for (int i = 0; i < companies.size(); i++) {
            com.Banco.model.Investment.InvestmentCompany c = companies.get(i);
            data[i][0] = c.getCode();
            data[i][1] = c.getName();
            data[i][2] = c.getRiskDescription();
            data[i][3] = String.format("%.0f%%", c.getReliability() * 100);
            data[i][4] = String.format("%.1f%%", c.getReturnPercentage() * 100);
        }
        return data;
    }
}
