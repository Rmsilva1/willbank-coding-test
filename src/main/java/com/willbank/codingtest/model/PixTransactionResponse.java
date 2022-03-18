package com.willbank.codingtest.model;

import java.math.BigDecimal;

public class PixTransactionResponse {
    private String originEmail;
    private String customerID;
    private String key;
    private BigDecimal value;
    private Boolean success;
    private String reason;

    public PixTransactionResponse() {
    }

    public String getReason() {
        return reason;
    }

    public PixTransactionResponse setReason(String reason) {
        this.reason = reason;
        return this;
    }

    public Boolean getSuccess() {
        return success;
    }

    public PixTransactionResponse setSuccess(Boolean success) {
        this.success = success;
        return this;
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
