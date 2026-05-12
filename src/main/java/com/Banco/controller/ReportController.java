package com.Banco.controller;

import com.Banco.service.ReportService;

public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    public String generateClientsReport() {
        return reportService.generateClientsReport();
    }

    public String generateEmployeesReport() {
        return reportService.generateEmployeesReport();
    }

    public String generateAccountsReport() {
        return reportService.generateAccountsReport();
    }

    public String generateSystemSummary() {
        return reportService.generateSystemSummary();
    }

    public Object[][] getClientsData() { return reportService.getClientsTableData(); }
    public Object[][] getEmployeesData() { return reportService.getEmployeesTableData(); }
    public Object[][] getAccountsData() { return reportService.getAccountsTableData(); }
    public Object[][] getCompaniesData() { return reportService.getCompaniesTableData(); }
}
