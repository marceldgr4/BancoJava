package com.Banco.model.domain.Person;

import java.util.Objects;

public abstract class Person {
    private String id;
    private String fullName;

    protected Person(String id, String fullName) {
        if (id == null || id.isBlank()) throw new IllegalArgumentException(
                "Person id  must not be blank."
        );
        if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException(
                "name must not be blank"
        );

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
        if (fullName == null || fullName.isBlank()) throw new IllegalArgumentException("name must not be blank.");
        this.fullName = fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Person other)) return false;
        return id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("Person [id=%s, fullName=%s]", getClass().getSimpleName(), id, fullName);
    }
}
