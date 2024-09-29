package com.learn.apply_credit_card_service.messageGateway;

import com.learn.apply_credit_card_service.event.NewCreditCardEvent;
import com.learn.apply_credit_card_service.service.EventPublisherService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Supplier;

@Slf4j
@AllArgsConstructor
@Configuration
public class CreditCardEventPublisher {

    private final EventPublisherService eventPublisherService;

    @Bean
    public Supplier<NewCreditCardEvent> publishNewCreditCardEvent(){
        return () -> {
            var newCreditCardEvent = eventPublisherService.publishEvent();
            return (newCreditCardEvent.isEmpty()) ? null : newCreditCardEvent.get();
        };
    }
}
