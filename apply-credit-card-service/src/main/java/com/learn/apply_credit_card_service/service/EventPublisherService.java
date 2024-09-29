package com.learn.apply_credit_card_service.service;

import com.learn.apply_credit_card_service.event.NewCreditCardEvent;

import java.util.Optional;

public interface EventPublisherService {
    Optional<NewCreditCardEvent> publishEvent();
}
