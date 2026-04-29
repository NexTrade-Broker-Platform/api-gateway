package com.lynx.apigateway.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MessageResponse(
        @JsonProperty("message")
        String message
) {
}