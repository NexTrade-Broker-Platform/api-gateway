package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlaceOrderResponse(
        @JsonProperty("message")
        String message,

        @JsonProperty("order")
        OrderDto order
) {
}