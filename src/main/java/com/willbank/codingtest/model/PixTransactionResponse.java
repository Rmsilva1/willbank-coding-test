package com.willbank.codingtest.model;

import java.math.BigDecimal;

public class PixTransactionResponse {
    private String originEmail;
    private String customerID;
    private String key;
    private BigDecimal value;

    public PixTransactionResponse() {
    }

    public String getCustomerID() {
        return customerID;
    }

    public PixTransactionResponse setCustomerID(String customerID) {
        this.customerID = customerID;
        return this;
    }

    public String getOriginEmail() {
        return originEmail;
    }

    public PixTransactionResponse setOriginEmail(String originEmail) {
        this.originEmail = originEmail;
        return this;
    }

    public String getKey() {
        return key;
    }

    public PixTransactionResponse setKey(String key) {
        this.key = key;
        return this;
    }

    public BigDecimal getValue() {
        return value;
    }

    public PixTransactionResponse setValue(BigDecimal value) {
        this.value = value;
        return this;
    }
}
