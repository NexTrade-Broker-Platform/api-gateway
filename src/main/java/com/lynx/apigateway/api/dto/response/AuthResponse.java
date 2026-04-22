package com.lynx.apigateway.api.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthResponse(
        @JsonProperty("message")
        String message,

        @JsonProperty("token")
        String token,

        @JsonProperty("user")
        UserDto user
) {
}