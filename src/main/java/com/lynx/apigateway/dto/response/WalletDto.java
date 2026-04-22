package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record WalletDto(
        @JsonProperty("id")
        UUID id,

        @JsonProperty("user_id")
        UUID userId,

        @JsonProperty("currency")
        String currency,

        @JsonProperty("available_balance")
        BigDecimal availableBalance,

        @JsonProperty("reserved_balance")
        BigDecimal reservedBalance,

        @JsonProperty("updated_at")
        LocalDateTime updatedAt,

        @JsonProperty("created_at")
        LocalDateTime createdAt,

        @JsonProperty("is_active")
        boolean isActive
) {
}