package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record StockDto(
        @JsonProperty("ticker")
        String ticker,

        @JsonProperty("name")
        String name,

        @JsonProperty("sector")
        String sector,

        @JsonProperty("current_price")
        BigDecimal currentPrice,

        @JsonProperty("open_price")
        BigDecimal openPrice,

        @JsonProperty("high_price")
        BigDecimal highPrice,

        @JsonProperty("low_price")
        BigDecimal lowPrice,

        @JsonProperty("volume")
        Long volume,

        @JsonProperty("listed_at")
        LocalDateTime listedAt
) {
}