package com.willbank.codingtest.service;

import com.willbank.codingtest.model.BankCustomersResponse;
import com.willbank.codingtest.model.Customer;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BankClientsService {
    Customer findCustomerAccount(String customerID);

    List<BankCustomersResponse> findAllBankCustomers();
}
