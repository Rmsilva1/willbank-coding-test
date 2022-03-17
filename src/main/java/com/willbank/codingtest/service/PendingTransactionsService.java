package com.willbank.codingtest.service;

import com.willbank.codingtest.model.PendingTransaction;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PendingTransactionsService {
    List<PendingTransaction> findAllPendingTransactions();
}
