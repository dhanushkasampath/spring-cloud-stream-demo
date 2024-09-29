package com.learn.credit_card_verification_service.event;

import lombok.Builder;
import lombok.Data;

/**
 * This bean is used to send the data to rabbitmq
 */
@Data
@Builder
public class CreditCardVerificationStatus {

    private String firstName;
    private String lastName;
    private Integer annualIncome;
    private String address;
    private String refId;
    private VerificationStatus status;

    // we can define enums inside classes also.
    public enum VerificationStatus{
        APPROVED,
        REJECTED
    }
}
