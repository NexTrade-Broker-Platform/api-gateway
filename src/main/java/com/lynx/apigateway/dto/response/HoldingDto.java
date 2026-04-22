package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record HoldingDto(
        @JsonProperty("ticker")
        String ticker,

        @JsonProperty("instrument_type")
        String instrumentType,

        @JsonProperty("quantity")
        int quantity,

        @JsonProperty("average_cost")
        BigDecimal averageCost
) {
}