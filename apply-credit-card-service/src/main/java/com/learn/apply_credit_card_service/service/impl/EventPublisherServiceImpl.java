package com.learn.apply_credit_card_service.service.impl;

import com.learn.apply_credit_card_service.event.ApplicationDetail;
import com.learn.apply_credit_card_service.event.NewCreditCardEvent;
import com.learn.apply_credit_card_service.repository.CreditCardRepository;
import com.learn.apply_credit_card_service.service.EventPublisherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class EventPublisherServiceImpl implements EventPublisherService {
    private final CreditCardRepository creditCardRepository;

    public Optional<NewCreditCardEvent> publishEvent(){
        var creditCardList = creditCardRepository.findByPublishStatus(false);
        var applicationDetailList = creditCardList.stream().map(creditCard -> {
            var applicationDetail = ApplicationDetail.builder().build();
            BeanUtils.copyProperties(creditCard, applicationDetail); // copy all the properties via property names
            creditCard.setPublishStatus(true);
            return applicationDetail;
        }).toList();

       log.info("#### Checked for new credit card request , available record to publish : {}", creditCardList.size());
       Optional<NewCreditCardEvent> eventToPublish = Optional.empty();

       if(!applicationDetailList.isEmpty()){
            log.info("******* Updating credit card status as published ******");
           var newCreditCardEvent = NewCreditCardEvent.builder()
                   .creditCardApplication(applicationDetailList)
                   .build();

           creditCardRepository.saveAll(creditCardList);
           eventToPublish = Optional.of(newCreditCardEvent);
       }
        return eventToPublish;
    }
}
