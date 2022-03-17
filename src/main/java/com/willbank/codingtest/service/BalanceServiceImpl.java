package com.willbank.codingtest.service;

import com.willbank.codingtest.model.BalanceResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class BalanceServiceImpl implements BalanceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PixResendServiceImpl.class);
    private final RestTemplate restTemplate;

    public BalanceServiceImpl(@Qualifier("balance_api") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public BalanceResponse getCustomerBalance(String agency, String account) {
        ResponseEntity<BalanceResponse> customerBalanceResponse = restTemplate
                .exchange("/balance/api/v1/" + agency + "/" + account,
                        HttpMethod.GET,
                        null,
                        new ParameterizedTypeReference<>() {
                        });

        if (customerBalanceResponse.hasBody()) {
            return customerBalanceResponse.getBody();
        }
        return null;
    }
}
