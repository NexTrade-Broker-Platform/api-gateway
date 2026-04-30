package com.lynx.apigateway.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;

public record PlaceOrderResponse(
        @JsonProperty("message")
        String message,

        @JsonProperty("order")
        OrderDto order
) {
}