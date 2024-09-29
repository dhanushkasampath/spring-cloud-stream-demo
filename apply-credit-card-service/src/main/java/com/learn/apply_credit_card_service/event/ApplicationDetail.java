package com.learn.apply_credit_card_service.event;

import lombok.Builder;
import lombok.Data;

/**
 * This bean is used to send the data over rabbitmq
 */
@Data
@Builder
public class ApplicationDetail {

    private String firstName;
    private String lastName;
    private Integer annualIncome;
    private String address;
    private String refId;
}
