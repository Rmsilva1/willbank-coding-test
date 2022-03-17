package com.willbank.codingtest.model;

public class Customer {
    private String customerID;
    private String agency;
    private String account;

    public Customer() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public Customer setCustomerID(String customerID) {
        this.customerID = customerID;
        return this;
    }

    public String getAgency() {
        return agency;
    }

    public Customer setAgency(String agency) {
        this.agency = agency;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public Customer setAccount(String account) {
        this.account = account;
        return this;
    }
}
