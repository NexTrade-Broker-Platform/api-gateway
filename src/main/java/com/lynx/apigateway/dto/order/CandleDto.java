package com.lynx.apigateway.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record CandleDto(
        @JsonProperty("timestamp")
        String timestamp,

        @JsonProperty("open")
        BigDecimal open,

        @JsonProperty("high")
        BigDecimal high,

        @JsonProperty("low")
        BigDecimal low,

        @JsonProperty("close")
        BigDecimal close,

        @JsonProperty("volume")
        Long volume
) {
}