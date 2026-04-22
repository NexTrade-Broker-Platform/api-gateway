package com.lynx.apigateway.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
        @NotBlank(message = "Email cannot be empty.")
        @Email(message = "The email address is invalid.")
        @JsonProperty("email")
        String email,

        @NotBlank(message = "Password cannot be empty.")
        @Size(min = 8, message = "Password must be at least 8 characters.")
        @JsonProperty("password")
        String password
) {
}