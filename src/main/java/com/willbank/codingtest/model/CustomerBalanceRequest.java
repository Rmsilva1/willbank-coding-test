package com.willbank.codingtest.model;

public class CustomerBalanceRequest {
    private String agency;
    private String account;

    public CustomerBalanceRequest() {
    }

    public String getAgency() {
        return agency;
    }

    public CustomerBalanceRequest setAgency(String agency) {
        this.agency = agency;
        return this;
    }

    public String getAccount() {
        return account;
    }

    public CustomerBalanceRequest setAccount(String account) {
        this.account = account;
        return this;
    }
}
