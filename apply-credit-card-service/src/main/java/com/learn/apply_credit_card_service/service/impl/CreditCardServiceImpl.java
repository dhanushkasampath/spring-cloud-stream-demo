package com.learn.apply_credit_card_service.service.impl;

import com.learn.apply_credit_card_service.dto.CreditCardRequest;
import com.learn.apply_credit_card_service.entity.CreditCard;
import com.learn.apply_credit_card_service.repository.CreditCardRepository;
import com.learn.apply_credit_card_service.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.text.RandomStringGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreditCardServiceImpl implements CreditCardService {

    private final CreditCardRepository creditCardRepository;

    @Override
    public Optional<String> saveApplication(CreditCardRequest creditCardRequest) {
        try {
            var creditCard = new CreditCard();
            BeanUtils.copyProperties(creditCardRequest, creditCard);
            creditCard.setPublishStatus(false);
            RandomStringGenerator generator = new RandomStringGenerator.Builder()
                    .withinRange('0', 'z')
                    .filteredBy(Character::isLetterOrDigit)
                    .build();

            creditCard.setRefId(generator.generate(10));
            var savedCreditCard = creditCardRepository.save(creditCard);
            return Optional.of(savedCreditCard.getRefId());
        } catch (Exception exception) {
            log.error(exception.getMessage());
            return Optional.empty();
        }
    }
}
