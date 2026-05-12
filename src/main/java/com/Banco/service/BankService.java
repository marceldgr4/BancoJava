package com.Banco.service;

import com.Banco.model.Investment.InvestmentCompany;
import com.Banco.repository.*;

import java.util.List;
import java.util.Optional;

public class BankService {
 private final ClientRepository clientRepository;
 private final EmployeeRepository employeeRepository;
 private final AccountRepository accountRepository;
 private final CompanyRepository companyRepository;
 private final TransactionRepository transactionRepository;

 private final ClientService clientService;
 private final AccountService accountService;
 private final EmployeeService employeeService;
 private final TransactionService transactionService;
 private final ReportService reportService;

    public BankService() {
        this.clientRepository = new ClientRepository();
        this.employeeRepository = new EmployeeRepository();
        this.accountRepository = new AccountRepository(clientRepository);
        this.companyRepository =  new CompanyRepository();
        this.transactionRepository = new TransactionRepository(accountRepository);

        this.clientService = new ClientService(clientRepository);
        this.accountService = new AccountService(accountRepository,clientRepository);
        this.employeeService = new EmployeeService(employeeRepository);
        this.transactionService = new TransactionService(transactionRepository);
        this.reportService = new ReportService(clientService, employeeService, accountService, companyRepository);
    }

    public ClientService clients(){
     return clientService;
    }
    public AccountService accounts(){
     return accountService;
    }
    public EmployeeService employees(){
     return employeeService;
    }
    public TransactionService transactions(){
     return transactionService;
    }
    public ReportService reports(){
     return reportService;
    }

    public void addCompany(InvestmentCompany company) {
        if (companyRepository.existsByCode(company.getCode()))
            throw new IllegalArgumentException("Company with code '" + company.getCode() + "' already exists");
        companyRepository.save(company);
    }
    public Optional<InvestmentCompany> findCompanyByCode(String code){
     return companyRepository.findByCode(code);
    }
    public List<InvestmentCompany> getAllCompanies(){
        return companyRepository.findAll();
    }
}
