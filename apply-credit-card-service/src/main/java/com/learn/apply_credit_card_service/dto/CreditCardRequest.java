package com.learn.apply_credit_card_service.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
@Builder
public class CreditCardRequest {

    @NotEmpty
    @NonNull
    @Schema(description = "Customer First Name", example = "John")//Replace @ApiModelProperty with @Schema:
    private String firstName;

    @NotEmpty
    @NonNull
    @Schema(description = "Customer Last Name", example = "Barns")
    private String lastName;

    @NotEmpty
    @NonNull
    @Schema(description = "Customer Annual Income", example = "450000")
    private Integer annualIncome;

    @NotEmpty
    @NonNull
    @Schema(description = "Customer Address", example = "No 456/4, New Jerssy, Australia")
    private String address;

}
