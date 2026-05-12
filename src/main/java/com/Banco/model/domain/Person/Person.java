package com.Banco.model.domain.Person;

import java.util.Objects;

public abstract class Person {
    private final int id;
    private String fullName;

    protected Person(int id, String fullName) {
        if (id <= 0)
            throw new IllegalArgumentException("Person id must be a positive integer, got: " + id);

        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException("Full name must not be blank");

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
        if (fullName == null || fullName.isBlank())
            throw new IllegalArgumentException(" full name must not be blank.");
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
