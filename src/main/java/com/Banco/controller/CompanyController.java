package com.Banco.controller;

import com.Banco.model.Investment.InvestmentCompany;
import com.Banco.service.CompanyService;

import java.util.List;

public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        if (companyService == null)
            throw new IllegalArgumentException("CompanyService cannot be null");
        this.companyService = companyService;
    }

    // ── Create ────────────────────────────────────────────────────────────────

    public InvestmentCompany registerCompany(String code, String name,
                                             double returnPercentage,
                                             int riskLevel,
                                             double reliability) {
        return companyService.registerCompany(code, name, returnPercentage, riskLevel, reliability);
    }

    // ── Read ──────────────────────────────────────────────────────────────────

    public InvestmentCompany getByCode(String code) {
        return companyService.getByCode(code);
    }

    public List<InvestmentCompany> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    // ── Update ────────────────────────────────────────────────────────────────

    public InvestmentCompany updateCompany(String code, String name,
                                           double returnPercentage,
                                           int riskLevel,
                                           double reliability) {
        return companyService.updateCompany(code, name, returnPercentage, riskLevel, reliability);
    }

    // ── Delete ────────────────────────────────────────────────────────────────

    public void deleteCompany(String code) {
        companyService.deleteCompany(code);
    }

    public int getTotalCompanies() {
        return companyService.getTotalCompanies();
    }
}