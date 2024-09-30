package com.learn.credit_card_generation_service.service;

import com.learn.credit_card_generation_service.event.CreditCardVerificationStatus;

import java.util.List;

public interface CreditCardService {
    void generateCreditCardNumberAndCvv(List<CreditCardVerificationStatus> creditCardApplicationDetailList);
}
