package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record PortfolioTimeseriesPointDto(
        @JsonProperty("date")
        String date,

        @JsonProperty("cash_value")
        BigDecimal cashValue,

        @JsonProperty("stocks_value")
        BigDecimal stocksValue,

        @JsonProperty("total_value")
        BigDecimal totalValue
) {
}
