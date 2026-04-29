package com.lynx.apigateway.dto.wallet;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WalletBalanceResponse(
        @JsonProperty("wallet")
        WalletDto wallet
) {
}