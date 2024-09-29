package com.learn.credit_card_verification_service.event;

import lombok.Builder;
import lombok.Data;

/**
 * This bean is used to receive the data comes in rabbitmq
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
