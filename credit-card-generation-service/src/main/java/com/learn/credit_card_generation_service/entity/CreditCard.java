package com.learn.credit_card_generation_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "T_CREDIT_CARD_DETAILS")
public class CreditCard {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "REFERENCE_ID")
    private String refId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "APPLICATION_VERIFICATION_STATUS")
    private String status; // approved or rejected

    @Column(name = "CREDIT_CARD_NUMBER")
    private Long creditCardNumber;

    @Column(name = "CREDIT_CARD_CVV")
    private Long cvv;
}
