package com.learn.credit_card_generation_service.service.impl;

import com.learn.credit_card_generation_service.entity.CreditCard;
import com.learn.credit_card_generation_service.event.CreditCardVerificationStatus;
import com.learn.credit_card_generation_service.repository.CreditCardRepository;
import com.learn.credit_card_generation_service.service.CreditCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@AllArgsConstructor
@Slf4j
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;
    @Override
    public void generateCreditCardNumberAndCvv(List<CreditCardVerificationStatus> creditCardVerificationStatusList) {
        var creditCardList = creditCardVerificationStatusList.stream().map(creditCardVerificationStatus -> {
            var creditCard = new CreditCard();
            BeanUtils.copyProperties(creditCardVerificationStatus, creditCard);
            if(creditCardVerificationStatus.getStatus().equals(CreditCardVerificationStatus.VerificationStatus.APPROVED)){
                log.info("*** Credit card number will generate as application is approved : {}",
                        creditCardVerificationStatus.getRefId());
                var creditCardNumber = generateRandomInt(100000000000L, 999999999999L);
                creditCard.setCreditCardNumber(creditCardNumber);
                var cvv = generateRandomInt(100L, 999L);
                creditCard.setCvv(cvv);
                creditCard.setStatus(creditCardVerificationStatus.getStatus().name());
            }else{
                log.info("*** Credit card number will not be generated as application is rejected : {}",
                        creditCardVerificationStatus.getRefId());
                creditCard.setCreditCardNumber(0L);
                creditCard.setCvv(0L);
            }
            return creditCard;
        }).toList();

        log.info("**** Saving Credit Card Details ****");
        creditCardRepository.saveAll(creditCardList);
        log.info("**** Credit Card Details Saved ****");
    }

    private Long generateRandomInt(Long min, Long max) {
        return ThreadLocalRandom.current().nextLong(min, max);
    }
}
