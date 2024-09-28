package com.learn.apply_credit_card_service.controller;

import com.learn.apply_credit_card_service.dto.CreditCardRequest;
import com.learn.apply_credit_card_service.service.CreditCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/applyCreditCard/v1")
public class CreditCardController {

    private CreditCardService creditCardService;

    @Operation(summary = "Create a credit card application for new customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    @PostMapping("/applyCreditCard")
    public ResponseEntity<String> createNewCreditCardRequest
            (@RequestBody CreditCardRequest creditCardRequest){

        var applicationRefId = creditCardService.saveApplication(creditCardRequest);
        return applicationRefId.isEmpty()? ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build()
                : ResponseEntity.status(HttpStatus.OK)
                .body("Your application reference number is : " + applicationRefId.get());
    }
}
