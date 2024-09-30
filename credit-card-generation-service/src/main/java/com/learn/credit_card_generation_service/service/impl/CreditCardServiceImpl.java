package com.learn.credit_card_generation_service.service.impl;

import com.learn.credit_card_generation_service.event.CreditCardVerificationStatus;
import com.learn.credit_card_generation_service.repository.CreditCardRepository;
import com.learn.credit_card_generation_service.service.CreditCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;
    @Override
    public void generateCreditCardNumberAndCvv(List<CreditCardVerificationStatus> creditCardApplicationDetailList) {

    }
}
