package com.willbank.codingtest.model;

import java.util.ArrayList;
import java.util.List;

public class PendingTransactionResponse {
    private List<PendingTransaction> pendingTransactions = new ArrayList<>();

    public PendingTransactionResponse() {
    }

    public List<PendingTransaction> getPendingTransactions() {
        return pendingTransactions;
    }

    public PendingTransactionResponse setPendingTransactions(List<PendingTransaction> pendingTransactions) {
        this.pendingTransactions = pendingTransactions;
        return this;
    }
}
