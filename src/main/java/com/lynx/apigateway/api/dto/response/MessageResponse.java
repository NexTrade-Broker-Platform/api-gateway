package com.lynx.apigateway.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MessageResponse(
        @JsonProperty("message")
        String message
) {
}