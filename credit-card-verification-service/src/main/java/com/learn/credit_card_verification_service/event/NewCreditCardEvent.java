package com.learn.credit_card_verification_service.event;

import com.learn.credit_card_verification_service.enums.EventType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class NewCreditCardEvent {

    private EventType event = EventType.NEW_CREDIT_CARD;
    private List<ApplicationDetail> creditCardApplication;
}
