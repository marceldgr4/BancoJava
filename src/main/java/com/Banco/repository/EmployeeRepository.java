package com.Banco.repository;

import com.Banco.model.domain.Employee.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    private final List<Employee> store = new ArrayList<>();
    public void save(Employee employee) {
        if (employee == null)
            throw new IllegalArgumentException("Employee cannot be null");
        store.add(employee);
    }

    public Optional<Employee> findById(int id) {
        return store.stream().filter(e -> e.getId() == id).findFirst();
    }

    public boolean existsById(int id) {
        return findById(id).isPresent();
    }

    public List<Employee> findAll() {
        return Collections.unmodifiableList(store);
    }
    public boolean delete(Employee employee) {
        return store.remove(employee);
    }
    public int count(){
        return store.size();
    }



}
