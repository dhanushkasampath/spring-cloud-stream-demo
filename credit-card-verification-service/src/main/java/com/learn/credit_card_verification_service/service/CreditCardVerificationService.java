package com.learn.credit_card_verification_service.service;

import com.learn.credit_card_verification_service.event.NewCreditCardEvent;
import com.learn.credit_card_verification_service.event.VerifyCreditCardEvent;

public interface CreditCardVerificationService {
    VerifyCreditCardEvent verifyCreditCardApplication(NewCreditCardEvent newCreditCardEvent);
}
