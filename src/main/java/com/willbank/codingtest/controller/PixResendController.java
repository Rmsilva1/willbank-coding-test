package com.willbank.codingtest.controller;

import com.willbank.codingtest.model.PixTransactionResponse;
import com.willbank.codingtest.service.PixResendService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/pix-resend")
public class PixResendController {
    private final PixResendService pixResendService;

    public PixResendController(PixResendService pixResendService) {
        this.pixResendService = pixResendService;
    }

    @PostMapping(path = "/{customerEmail}")
    public PixTransactionResponse resendPixTransactionByEmail(@PathVariable String customerEmail) {
        return pixResendService.resendPixTransaction(customerEmail);
    }
}
