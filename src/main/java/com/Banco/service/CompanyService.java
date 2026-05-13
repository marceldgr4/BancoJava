package com.Banco.service;

import com.Banco.exceptions.DuplicateResourceException;
import com.Banco.exceptions.ValidationException;
import com.Banco.model.Investment.InvestmentCompany;
import com.Banco.repository.CompanyRepository;

import java.util.List;

public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        if (companyRepository == null)
            throw new IllegalArgumentException("CompanyRepository cannot be null");
        this.companyRepository = companyRepository;
    }

    // ── Create ────────────────────────────────────────────────────────────────

    public InvestmentCompany registerCompany(String code, String name,
                                             double returnPercentage,
                                             int riskLevel,
                                             double reliability) {
        if (companyRepository.existsByCode(code))
            throw new DuplicateResourceException(
                    "Investment company with code '" + code + "' already exists.");

        InvestmentCompany company = new InvestmentCompany(
                code, name, returnPercentage, riskLevel, reliability);
        companyRepository.save(company);
        return company;
    }

    // ── Read ──────────────────────────────────────────────────────────────────

    public InvestmentCompany getByCode(String code) {
        return companyRepository.findByCode(code)
                .orElseThrow(() -> new ValidationException(
                        "Investment company with code '" + code + "' not found."));
    }

    public List<InvestmentCompany> getAllCompanies() {
        return companyRepository.findAll();
    }

    public boolean existsByCode(String code) {
        return companyRepository.existsByCode(code);
    }

    // ── Update ────────────────────────────────────────────────────────────────

    /**
     * Actualiza los campos editables de una empresa inversora.
     * El código (PK) no se puede cambiar.
     */
    public InvestmentCompany updateCompany(String code,
                                           String name,
                                           double returnPercentage,
                                           int riskLevel,
                                           double reliability) {
        InvestmentCompany company = getByCode(code);
        company.setName(name);
        company.setReturnPercentage(returnPercentage);
        company.setRiskLevel(riskLevel);
        company.setReliability(reliability);
        return company;
    }

    // ── Delete ────────────────────────────────────────────────────────────────

    public void deleteCompany(String code) {
        InvestmentCompany company = getByCode(code);
        boolean removed = companyRepository.delete(company);
        if (!removed)
            throw new ValidationException(
                    "Could not remove company '" + code + "' from repository.");
    }

    public int getTotalCompanies() {
        return companyRepository.findAll().size();
    }
}