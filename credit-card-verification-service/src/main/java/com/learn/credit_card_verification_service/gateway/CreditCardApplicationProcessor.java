package com.learn.credit_card_verification_service.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.credit_card_verification_service.event.NewCreditCardEvent;
import com.learn.credit_card_verification_service.event.VerifyCreditCardEvent;
import com.learn.credit_card_verification_service.service.CreditCardVerificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.function.Function;

@Configuration
@AllArgsConstructor
@Slf4j
public class CreditCardApplicationProcessor {

    private final CreditCardVerificationService creditCardVerificationService;

    /**
     * This Function gets NewCreditCardEvent as input and returns VerifyCreditCardEvent as output
     * @return
     */
//    @Bean
//    public Function<NewCreditCardEvent, VerifyCreditCardEvent> verifyCreditCardApplication(){
//        return newCreditCardEvent -> {
//            var verifyCreditCardEvent = creditCardVerificationService.verifyCreditCardApplication(newCreditCardEvent);
//            log.info("**** Publishing credit card application verification status : {} ****",
//                    verifyCreditCardEvent.getCreditCardApplicationDetailList().size());
//            return verifyCreditCardEvent.getCreditCardApplicationDetailList().isEmpty() ? null : verifyCreditCardEvent;
//        };
//    }

    /**
     * I had to introduce below function since this consumer was unable to deserialize the payload comes in rabbit. Here I have used object mapper to do it manually
     * @param objectMapper
     * @return
     */
    @Bean
    public Function<byte[], VerifyCreditCardEvent> verifyCreditCardApplication(ObjectMapper objectMapper){
        return message -> {
            NewCreditCardEvent newCreditCardEvent = null;
            try {
                newCreditCardEvent = objectMapper.readValue(message, NewCreditCardEvent.class);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            var verifyCreditCardEvent = creditCardVerificationService.verifyCreditCardApplication(newCreditCardEvent);
            log.info("**** Publishing credit card application verification status : {} ****",
                    verifyCreditCardEvent.getCreditCardApplicationDetailList().size());
            return verifyCreditCardEvent.getCreditCardApplicationDetailList().isEmpty() ? null : verifyCreditCardEvent;
        };
    }

}
