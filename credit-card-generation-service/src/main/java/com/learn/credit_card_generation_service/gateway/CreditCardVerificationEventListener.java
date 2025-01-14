package com.learn.credit_card_generation_service.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.credit_card_generation_service.event.VerifyCreditCardEvent;
import com.learn.credit_card_generation_service.service.CreditCardService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.function.Consumer;

@AllArgsConstructor
@Slf4j
@Configuration
public class CreditCardVerificationEventListener {

    private final CreditCardService creditCardService;

//    @Bean
//    public Consumer<VerifyCreditCardEvent> generateCreditCard(){
//        return verifyCreditCardEvent -> {
//            log.info("Received credit card application : {}",
//                    verifyCreditCardEvent.getCreditCardApplicationDetailList().size());
//            creditCardService.generateCreditCardNumberAndCvv(verifyCreditCardEvent.getCreditCardApplicationDetailList());
//        };
//    }

    @Bean
    public Consumer<byte[]> generateCreditCard(ObjectMapper objectMapper){
        return message -> {

            VerifyCreditCardEvent verifyCreditCardEvent = null;
            try {
                verifyCreditCardEvent = objectMapper.readValue(message, VerifyCreditCardEvent.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            log.info("Received credit card application : {}",
                    verifyCreditCardEvent.getCreditCardApplicationDetailList().size());
            creditCardService.generateCreditCardNumberAndCvv(verifyCreditCardEvent.getCreditCardApplicationDetailList());
        };
    }
}
