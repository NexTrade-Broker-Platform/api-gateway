package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        @JsonProperty("id")
        UUID id,

        @JsonProperty("email")
        String email,

        @JsonProperty("first_name")
        String firstName,

        @JsonProperty("last_name")
        String lastName,

        @JsonProperty("username")
        String username,

        @JsonProperty("date_of_birth")
        LocalDate dateOfBirth,

        @JsonProperty("created_at")
        LocalDateTime createdAt,

        @JsonProperty("is_active")
        boolean isActive
) {
}