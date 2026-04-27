package com.lynx.apigateway.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record WithdrawRequest(
        @NotNull(message = "Amount cannot be null.")
        @DecimalMin(value = "0.01", message = "Withdrawal amount must be strictly greater than 0.")
        @JsonProperty("amount")
        BigDecimal amount,

        @NotBlank(message = "Currency cannot be empty.")
        @JsonProperty("currency")
        String currency
) {
}