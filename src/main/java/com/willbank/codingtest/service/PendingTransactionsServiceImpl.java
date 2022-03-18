package com.willbank.codingtest.service;

import com.willbank.codingtest.model.PendingTransaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PendingTransactionsServiceImpl implements PendingTransactionsService {
    private static final Logger logger = LoggerFactory.getLogger(PendingTransactionsServiceImpl.class);
    private final RestTemplate restTemplate;

    public PendingTransactionsServiceImpl(@Qualifier("pending_transactions_api") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<PendingTransaction> findAllPendingTransactions() {
        ResponseEntity<List<PendingTransaction>> pendingTransactionResponse = restTemplate
                .exchange("/pending-transactions/api/v1",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });

        if (pendingTransactionResponse.hasBody()) {
            return pendingTransactionResponse.getBody();
        }

        return null;
    }
}
