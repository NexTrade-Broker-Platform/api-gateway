package com.lynx.apigateway.dto.auth;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UserDto(
        @JsonProperty("id")
        UUID id,

        @JsonProperty("email")
        String email,

        @JsonProperty("username")
        String username,

        @JsonProperty("firstName")
        String firstName,

        @JsonProperty("lastName")
        String lastName,

        @JsonProperty("dateOfBirth")
        LocalDate dateOfBirth,

        @JsonProperty("createdAt")
        LocalDateTime createdAt,

        @JsonProperty("active")
        @JsonAlias({"active"})
        boolean active,

        @JsonProperty("isAdmin")
        @JsonAlias({"admin"})
        Boolean isAdmin
) {
}