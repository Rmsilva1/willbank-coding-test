package com.willbank.codingtest.service;

import com.willbank.codingtest.model.PendingTransaction;
import com.willbank.codingtest.model.PixTransactionResponse;
import com.willbank.codingtest.model.exception.ApiException;
import com.willbank.codingtest.model.exception.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class PixResendServiceImpl implements PixResendService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PixResendServiceImpl.class);

    private final PendingTransactionsService pendingTransactionsService;
    private final BalanceService balanceService;
    private final BankClientsService bankClientsService;

    public PixResendServiceImpl(PendingTransactionsService pendingTransactionsService, BalanceService balanceService, BankClientsService bankClientsService) {
        this.pendingTransactionsService = pendingTransactionsService;
        this.balanceService = balanceService;
        this.bankClientsService = bankClientsService;
    }

    public PixTransactionResponse resendPixTransaction(String email) {
        LOGGER.info("stage=init method=PixResendServiceImpl.resendPixTransaction email={}", email);

        var pendingCustomerTransaction = findPendingCustomerTransaction(email);

        return performPixTransaction(pendingCustomerTransaction);
    }

    private PendingTransaction findPendingCustomerTransaction(String email) {
        var pendingTransactions = pendingTransactionsService.findAllPendingTransactions();

        var customerPendingTransaction = pendingTransactions.stream()
                .filter(transaction -> transaction.getEmail().equals(email)).findFirst();

        if (customerPendingTransaction.isPresent()) {
            return customerPendingTransaction.get();
        }

        LOGGER.error("stage=error method=PixResendServiceImpl.findPendingTransaction customer={}, has no pending transactions", email);
        throw new NotFoundException();
    }

    private PixTransactionResponse performPixTransaction(PendingTransaction pendingTransaction) {
        LOGGER.info("stage=init method=PixResendServiceImpl.performPixTransaction, customerID={}, value={} key={}",
                pendingTransaction.getCustomerID(), pendingTransaction.getValue(), pendingTransaction.getKey());

        var customerAccount = bankClientsService.findCustomerAccount(pendingTransaction.getCustomerID());

        if (Objects.isNull(customerAccount)) {
            LOGGER.error("stage=error method=PixResendServiceImpl.verifyCustomerBalance pix transaction" +
                    " failed, failed to find customer account pendingTransaction={}", pendingTransaction);
            throw new NotFoundException();
        }

        var balanceResponse = balanceService.getCustomerBalance(customerAccount.getAgency(), customerAccount.getAccount());

        if (Objects.isNull(balanceResponse)) {
            LOGGER.error("stage=error method=PixResendServiceImpl.performPixTransaction failed to check customer balance");
            throw new ApiException();
        }

        if (balanceResponse.getBalance().compareTo(pendingTransaction.getValue()) >= 0) {

            var response = new PixTransactionResponse()
                    .setCustomerID(pendingTransaction.getCustomerID())
                    .setKey(pendingTransaction.getKey())
                    .setValue(pendingTransaction.getValue())
                    .setOriginEmail(pendingTransaction.getEmail());

            LOGGER.info("stage=info method=PixResendServiceImpl.performPixTransaction sending pix transaction " +
                    "customerID={}, value={} key={}", response.getCustomerID(), response.getValue(), response.getKey());
            return response;
        }

        LOGGER.error("stage=error method=PixResendServiceImpl.verifyCustomerBalance pix transaction failed," +
                        " customerID={}, value={} key={}", pendingTransaction.getCustomerID(), pendingTransaction.getValue(),
                pendingTransaction.getKey());
        throw new ApiException();
    }
}
