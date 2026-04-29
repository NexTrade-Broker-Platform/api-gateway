package com.lynx.apigateway.dto.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DepositResponse(
        @JsonProperty("message")
        String message,

        @JsonProperty("wallet")
        WalletDto wallet,

        @JsonProperty("transaction")
        WalletTransactionDto transaction
) {
}