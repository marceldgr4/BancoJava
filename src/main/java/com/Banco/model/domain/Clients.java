package com.Banco.model.domain;

public class Clients {
    private String UserNumber;
    private String FullName;

    public Clients(String userNumber, String fullName) {
        UserNumber = userNumber;
        FullName = fullName;
    }

    public String getUserNumber() {
        return UserNumber;
    }

    public void setUserNumber(String userNumber) {
        UserNumber = userNumber;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }
}
