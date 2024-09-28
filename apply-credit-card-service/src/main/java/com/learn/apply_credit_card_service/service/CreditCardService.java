package com.learn.apply_credit_card_service.service;

import com.learn.apply_credit_card_service.dto.CreditCardRequest;

import java.util.Optional;

public interface CreditCardService {
    Optional<String> saveApplication(CreditCardRequest creditCardRequest);
}
