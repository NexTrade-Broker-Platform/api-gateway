package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record CancelOrderResponse(
        @JsonProperty("message")
        String message,

        @JsonProperty("order")
        CancelOrderDto order
) {
}