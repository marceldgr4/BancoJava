package com.Banco.model.domain.Person;


import com.Banco.util.validator.BaseValidator;
import com.Banco.util.validator.InputValidator;

import java.util.Objects;

public abstract class Person {
    private final int id;
    private String fullName;

    protected Person(int id, String fullName) {
       BaseValidator.requirePositive(id,"Person ID");
        InputValidator.validateName(fullName);

        this.id = id;
        this.fullName = fullName;
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        InputValidator.validateName(fullName);
          this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person other)) return false;
        return Objects.equals(id, other.id);
}
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%s[id=%s, name=%s]", getClass().getSimpleName(), id, fullName);
    }
}
