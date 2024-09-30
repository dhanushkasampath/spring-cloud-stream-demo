package com.learn.credit_card_generation_service.controller;

import com.learn.credit_card_generation_service.service.CreditCardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
@RequestMapping("/creditCardGeneration/v1")
public class CreditCardController {

    private CreditCardService creditCardService;

    @Operation(summary = "Credit Card Application Status")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "text/plain", schema = @Schema(implementation = String.class)))
    })
    @GetMapping("/applyCreditCard")
    public ResponseEntity<String> creditCardApplicationStatus
            (@RequestParam String applicationRefId){

        if(StringUtils.isEmpty(applicationRefId)){
            ResponseEntity.badRequest().build();
        }
        var status = creditCardService.getApplicationStatus(applicationRefId);
        return StringUtils.isEmpty(status)?
                ResponseEntity.status(HttpStatus.OK)
                .body("Your application is in progress") :
                ResponseEntity.status(HttpStatus.OK)
                .body("Your application with reference Id " + applicationRefId + " has been " + status);
    }
}
