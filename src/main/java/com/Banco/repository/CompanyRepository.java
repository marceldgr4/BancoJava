package com.Banco.repository;

import com.Banco.model.Investment.InvestmentCompany;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CompanyRepository {
    private final List<InvestmentCompany> store = new ArrayList<>();

    public void save(InvestmentCompany investmentCompany) {
        if (investmentCompany == null)
            throw new IllegalArgumentException("Investment company cannot be null");
        store.add(investmentCompany);
    }
    public Optional<InvestmentCompany> findByCode(String code){
        return store.stream()
                .filter(c-> c.getCode().equals(code))
                .findFirst();
    }
    public boolean existsByCode(String code){
        return findByCode(code).isPresent();
    }
    public List<InvestmentCompany> findAll(){
        return Collections.unmodifiableList(store);
    }
    public boolean delete(InvestmentCompany investmentCompany){
        return store.remove(investmentCompany);
    }
}
