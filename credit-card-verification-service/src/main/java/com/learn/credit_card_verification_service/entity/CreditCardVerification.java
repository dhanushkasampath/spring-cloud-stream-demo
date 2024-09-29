package com.learn.credit_card_verification_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "T_CREDIT_CARD_VERIFICATION")
public class CreditCardVerification {

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "ANNUAL_INCOME")
    private Integer annualIncome;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "APPLICATION_VERIFICATION_STATUS")
    private String status; // approved or rejected
}
