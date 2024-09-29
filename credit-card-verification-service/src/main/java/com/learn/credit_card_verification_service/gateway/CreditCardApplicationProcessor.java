package com.learn.credit_card_verification_service.gateway;

import com.learn.credit_card_verification_service.event.NewCreditCardEvent;
import com.learn.credit_card_verification_service.event.VerifyCreditCardEvent;
import com.learn.credit_card_verification_service.service.CreditCardVerificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
@AllArgsConstructor
@Slf4j
public class CreditCardApplicationProcessor {

    private final CreditCardVerificationService creditCardVerificationService;

    @Bean
    public Function<NewCreditCardEvent, VerifyCreditCardEvent> verifyCreditCardApplication(){
        return newCreditCardEvent -> {
            var verifyCreditCardEvent = creditCardVerificationService.verifyCreditCardApplication(newCreditCardEvent);
            log.info("**** Publishing credit card application verification status : {} ****",
                    verifyCreditCardEvent.getCreditCardApplicationDetailList().size());
            return verifyCreditCardEvent.getCreditCardApplicationDetailList().isEmpty() ? null : verifyCreditCardEvent;
        };
    }

}
