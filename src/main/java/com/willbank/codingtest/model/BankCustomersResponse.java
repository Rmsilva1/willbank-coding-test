package com.willbank.codingtest.model;

import java.util.List;

public class BankCustomersResponse {
    private String bank;
    private List<Customer> customers;

    public BankCustomersResponse() {
    }

    public String getBank() {
        return bank;
    }

    public BankCustomersResponse setBank(String bank) {
        this.bank = bank;
        return this;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public BankCustomersResponse setCustomers(List<Customer> customers) {
        this.customers = customers;
        return this;
    }
}
