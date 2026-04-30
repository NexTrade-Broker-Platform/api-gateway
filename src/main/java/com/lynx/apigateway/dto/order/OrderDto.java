package com.lynx.apigateway.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record OrderDto(
        @JsonProperty("order_id")
        String orderId,

        @JsonProperty("platform_id")
        String platformId,

        @JsonProperty("platform_user_id")
        String platformUserId,

        @JsonProperty("instrument_type")
        String instrumentType,

        @JsonProperty("instrument_id")
        String instrumentId,

        @JsonProperty("order_type")
        String orderType,

        @JsonProperty("side")
        String side,

        @JsonProperty("quantity")
        BigDecimal quantity,

        @JsonProperty("limit_price")
        BigDecimal limitPrice,

        @JsonProperty("status")
        String status,

        @JsonProperty("filled_quantity")
        BigDecimal filledQuantity,

        @JsonProperty("average_fill_price")
        BigDecimal averageFillPrice,

        @JsonProperty("exchange_fee")
        BigDecimal exchangeFee,

        @JsonProperty("created_at")
        LocalDateTime createdAt,

        @JsonProperty("updated_at")
        LocalDateTime updatedAt,

        @JsonProperty("expires_at")
        LocalDateTime expiresAt
) {
}