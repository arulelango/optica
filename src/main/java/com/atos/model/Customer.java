package com.atos.model;

public class Customer {
    private  long id;
    private  String firstName;
    private  String surName;

    public Customer(long id, String firstName, String surName){
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurName() {
        return surName;
    }
}
