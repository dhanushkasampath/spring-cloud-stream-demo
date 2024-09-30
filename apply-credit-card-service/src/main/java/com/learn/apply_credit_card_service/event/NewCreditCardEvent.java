package com.learn.apply_credit_card_service.event;

import com.learn.apply_credit_card_service.enums.EventType;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * We started working on this class after db saving successfully
 */
@Data
@Builder
public class NewCreditCardEvent {

    private EventType event;
    private List<ApplicationDetail> creditCardApplication;
}
