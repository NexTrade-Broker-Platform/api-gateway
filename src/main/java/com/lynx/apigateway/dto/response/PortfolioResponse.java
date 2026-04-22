package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PortfolioResponse(
        @JsonProperty("cash_balances")
        List<CashBalanceDto> cashBalances,

        @JsonProperty("holdings")
        List<HoldingDto> holdings
) {
}