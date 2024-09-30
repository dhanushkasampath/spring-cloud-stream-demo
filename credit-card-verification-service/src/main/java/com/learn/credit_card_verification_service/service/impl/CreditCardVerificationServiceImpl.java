package com.learn.credit_card_verification_service.service.impl;

import com.learn.credit_card_verification_service.entity.CreditCardVerification;
import com.learn.credit_card_verification_service.event.CreditCardVerificationStatus;
import com.learn.credit_card_verification_service.event.NewCreditCardEvent;
import com.learn.credit_card_verification_service.event.VerifyCreditCardEvent;
import com.learn.credit_card_verification_service.repository.CreditCardVerificationRepository;
import com.learn.credit_card_verification_service.service.CreditCardVerificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CreditCardVerificationServiceImpl implements CreditCardVerificationService {

    private final CreditCardVerificationRepository creditCardVerificationRepository;

    @Override
    public VerifyCreditCardEvent verifyCreditCardApplication(NewCreditCardEvent newCreditCardEvent) {
        var creditCardApplicationList = newCreditCardEvent.getCreditCardApplication();
        //below we are going to convert event data to entity
        var creditCardVerificationStatusList = creditCardApplicationList.stream().map(creditCardApplication -> {
            var creditCardVerificationStatus = CreditCardVerificationStatus.builder().build();
            BeanUtils.copyProperties(creditCardApplication, creditCardVerificationStatus);
            if (creditCardApplication.getAnnualIncome() > 4000) {
                creditCardVerificationStatus.setStatus(CreditCardVerificationStatus.VerificationStatus.APPROVED);
            } else {
                creditCardVerificationStatus.setStatus(CreditCardVerificationStatus.VerificationStatus.REJECTED);
            }
            return creditCardVerificationStatus;
        }).toList();

        saveCreditCardVerificationStatus(creditCardVerificationStatusList);

        return VerifyCreditCardEvent.builder()
                .creditCardApplicationDetailList(creditCardVerificationStatusList)
                .build();
    }

    private void saveCreditCardVerificationStatus(List<CreditCardVerificationStatus> creditCardVerificationStatusList) {

        var creditCardVerificationList = creditCardVerificationStatusList.stream().map(creditCardVerificationStatus -> {
            var creditCardVerification = new CreditCardVerification();
            BeanUtils.copyProperties(creditCardVerificationStatus, creditCardVerification);
            creditCardVerification.setStatus(creditCardVerificationStatus.getStatus().name());
            return creditCardVerification;
        }).toList();

        log.info("*** Saving credit card application status ***");
        creditCardVerificationRepository.saveAll(creditCardVerificationList);
    }
}
