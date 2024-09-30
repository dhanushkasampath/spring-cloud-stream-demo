package com.learn.credit_card_generation_service.event;

import com.learn.credit_card_generation_service.enums.EventType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class VerifyCreditCardEvent {

    private EventType event = EventType.APPROVE_CREDIT_CARD;
    private List<CreditCardVerificationStatus> creditCardApplicationDetailList;
}