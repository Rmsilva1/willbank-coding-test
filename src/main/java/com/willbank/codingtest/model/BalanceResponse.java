package com.willbank.codingtest.model;

import java.math.BigDecimal;

public class BalanceResponse {
    private BigDecimal balance;

    public BalanceResponse() {
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public BalanceResponse setBalance(BigDecimal balance) {
        this.balance = balance;
        return this;
    }
}
