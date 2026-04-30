package com.lynx.apigateway.dto.order;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record CancelOrderDto(
        @JsonProperty("id")
        String id,

        @JsonProperty("status")
        String status,

        @JsonProperty("updated_at")
        LocalDateTime updatedAt
) {
}