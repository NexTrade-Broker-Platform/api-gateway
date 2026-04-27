package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MessageResponse(
        @JsonProperty("message")
        String message
) {
}