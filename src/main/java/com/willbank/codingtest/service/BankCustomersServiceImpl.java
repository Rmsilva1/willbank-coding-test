package com.willbank.codingtest.service;

import com.willbank.codingtest.model.BankCustomersResponse;
import com.willbank.codingtest.model.Customer;
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
public class BankCustomersServiceImpl implements BankClientsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(BankCustomersServiceImpl.class);

    private final RestTemplate restTemplate;
    private final String WILL_BANK_NAME = "Will Bank";

    public BankCustomersServiceImpl(@Qualifier("bank_clients_api") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Customer findCustomerAccount(String customerID) {
        LOGGER.info("stage=init method=BankClientServiceImpl.findAllBankClients");

        var bankCustomers = findAllBankCustomers();

        var willBankCustomers = filterWillBank(bankCustomers);

        return filterCustomerAccount(willBankCustomers, customerID);
    }

    public List<BankCustomersResponse> findAllBankCustomers() {
        ResponseEntity<List<BankCustomersResponse>> bankCustomersResponse = restTemplate
                .exchange("/banks-customers/api/v1",
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });

        if (bankCustomersResponse.hasBody()) {
            return bankCustomersResponse.getBody();
        }
        return null;
    }

    private BankCustomersResponse filterWillBank(List<BankCustomersResponse> bankCustomersResponseList) {
        var willBankCustomerResponse = bankCustomersResponseList.stream()
                .filter(bankClientResponse -> bankClientResponse.getBank().equals(WILL_BANK_NAME)).findFirst();

        return willBankCustomerResponse.orElse(null);
    }

    private Customer filterCustomerAccount(BankCustomersResponse bankCustomersResponse, String customerID) {
        var customerAccount = bankCustomersResponse.getCustomers().stream()
                .filter(customer -> customer.getCustomerID().equals(customerID)).findFirst();

        return customerAccount.orElse(null);
    }
}
