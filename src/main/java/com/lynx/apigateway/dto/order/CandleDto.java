package com.lynx.apigateway.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CandleDto(
        @JsonProperty("timestamp")
        LocalDateTime timestamp,

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