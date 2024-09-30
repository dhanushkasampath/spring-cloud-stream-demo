package com.learn.apply_credit_card_service.service.impl;

import com.learn.apply_credit_card_service.enums.EventType;
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

    //by spring-cloud-stream this function get called in every second. I assume that time can be configured
    public Optional<NewCreditCardEvent> publishEvent(){
        var creditCardList = creditCardRepository.findByPublishStatus(false); // it fetches all non-published records from the table
        // below applicationDetailList is the object list that sent as en event to the rabbitmq
        var applicationDetailList = creditCardList.stream().map(creditCard -> {
            var applicationDetail = ApplicationDetail.builder().build(); // newly create ApplicationDetail object per each record in CreditCard entity
            BeanUtils.copyProperties(creditCard, applicationDetail); // copy all the properties via property names
            creditCard.setPublishStatus(true);
            return applicationDetail;
        }).toList();

       log.info("#### Checked for new credit card request , available record to publish : {}", creditCardList.size());
       Optional<NewCreditCardEvent> eventToPublish = Optional.empty(); // creating an event object to be sent to rabbit

       if(!applicationDetailList.isEmpty()){ // If found any non-published records in table execute below steps
           log.info("******* Updating credit card status as published ******");
           var newCreditCardEvent = NewCreditCardEvent.builder()// creating the event object embedding applicationDetailList
               .event(EventType.NEW_CREDIT_CARD)
               .creditCardApplication(applicationDetailList)
               .build();

           creditCardRepository.saveAll(creditCardList); // update the records in CreditCard table by setting the status as "published"
           eventToPublish = Optional.of(newCreditCardEvent);
       }
        return eventToPublish;
    }
}
