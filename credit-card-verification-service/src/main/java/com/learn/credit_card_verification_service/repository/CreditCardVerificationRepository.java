package com.learn.credit_card_verification_service.repository;


import com.learn.credit_card_verification_service.entity.CreditCardVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardVerificationRepository extends JpaRepository<CreditCardVerification, Long> {
}
