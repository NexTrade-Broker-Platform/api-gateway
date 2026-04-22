package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record WalletTransactionDto(
        @JsonProperty("id")
        UUID id,

        @JsonProperty("wallet_id")
        UUID walletId,

        @JsonProperty("reference_id")
        UUID referenceId,

        @JsonProperty("transaction_type")
        String transactionType,

        @JsonProperty("amount")
        BigDecimal amount,

        @JsonProperty("created_at")
        LocalDateTime createdAt
) {
}