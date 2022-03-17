package com.willbank.codingtest.service;

import com.willbank.codingtest.model.BalanceResponse;

public interface BalanceService {
    BalanceResponse getCustomerBalance(String agency, String account);
}
