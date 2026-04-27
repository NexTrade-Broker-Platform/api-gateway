package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OrderHistoryResponse(
        @JsonProperty("orders")
        List<OrderDto> orders,

        @JsonProperty("pagination")
        PaginationDto pagination
) {
}