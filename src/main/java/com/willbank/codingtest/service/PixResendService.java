package com.willbank.codingtest.service;

import com.willbank.codingtest.model.PixTransactionResponse;
import org.springframework.stereotype.Service;

@Service
public interface PixResendService {
    PixTransactionResponse resendPixTransaction(String email);
}
