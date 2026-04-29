package com.lynx.apigateway.dto.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CashBalanceDto(
        @JsonProperty("currency")
        String currency,

        @JsonProperty("available_balance")
        BigDecimal availableBalance,

        @JsonProperty("reserved_balance")
        BigDecimal reservedBalance
) {
}