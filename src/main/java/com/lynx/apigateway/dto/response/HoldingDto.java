package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record HoldingDto(
        @JsonProperty("ticker")
        String ticker,

        @JsonProperty("instrumentType")
        String instrumentType,

        @JsonProperty("quantity")
        int quantity,

        @JsonProperty("averageCost")
        BigDecimal averageCost
) {
}