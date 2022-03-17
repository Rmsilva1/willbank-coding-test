package com.willbank.codingtest.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Beans {

    @Value("${pending-transactions-api.host}")
    private String pendingTransactionsAPIHost;

    @Value("${balance-api.host}")
    private String balanceAPIHost;

    @Value("${bank-clients-api.host}")
    private String bankClientsAPIHost;

    @Bean
    @Qualifier("pending_transactions_api")
    public RestTemplate pendingTransactionsRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(pendingTransactionsAPIHost)
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }

    @Bean
    @Qualifier("balance_api")
    public RestTemplate balanceAPIRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(balanceAPIHost)
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }

    @Bean
    @Qualifier("bank_clients_api")
    public RestTemplate bankClientsAPIRestTemplate(RestTemplateBuilder builder) {
        return builder
                .rootUri(bankClientsAPIHost)
                .errorHandler(new RestTemplateErrorHandler())
                .build();
    }
}
