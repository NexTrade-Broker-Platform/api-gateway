package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WalletBalanceResponse(
        @JsonProperty("wallet")
        WalletDto wallet
) {
}