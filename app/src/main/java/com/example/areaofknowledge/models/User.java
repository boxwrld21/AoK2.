package com.example.areaofknowledge.models;

public class User {
    private String eT1, eT2;
    public User() {}

    public User(String eT1, String eT2) {
        this.eT1 = eT1;
        this.eT2 = eT2;
    }

    public String geteT1() {
        return eT1;
    }

    public void seteT1(String eT1) {
        this.eT1 = eT1;
    }

    public String geteT2() {
        return eT2;
    }

    public void seteT2(String eT2) {
        this.eT2 = eT2;
    }
}
