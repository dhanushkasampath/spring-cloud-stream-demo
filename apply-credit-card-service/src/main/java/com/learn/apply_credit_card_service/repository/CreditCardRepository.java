package com.learn.apply_credit_card_service.repository;

import com.learn.apply_credit_card_service.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
