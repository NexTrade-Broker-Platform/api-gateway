package com.lynx.apigateway.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AuthServiceResponse(
        @JsonProperty("message")
        String message,

        @JsonProperty("user")
        UserDto user
) {
}