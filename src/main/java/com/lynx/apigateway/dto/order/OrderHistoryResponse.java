package com.lynx.apigateway.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lynx.apigateway.dto.PaginationDto;

import java.util.List;

public record OrderHistoryResponse(
        @JsonProperty("orders")
        List<OrderDto> orders,

        @JsonProperty("pagination")
        PaginationDto pagination
) {
}