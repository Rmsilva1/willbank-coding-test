package com.willbank.codingtest.model;

import java.math.BigDecimal;

public class PendingTransaction {
    private String customerID;
    private String email;
    private String key;
    private BigDecimal value;
    private String bank;

    public PendingTransaction() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public PendingTransaction setCustomerID(String customerID) {
        this.customerID = customerID;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public PendingTransaction setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getKey() {
        return key;
    }

    public PendingTransaction setKey(String key) {
        this.key = key;
        return this;
    }

    public BigDecimal getValue() {
        return value;
    }

    public PendingTransaction setValue(BigDecimal value) {
        this.value = value;
        return this;
    }

    public String getBank() {
        return bank;
    }

    public PendingTransaction setBank(String bank) {
        this.bank = bank;
        return this;
    }
}
