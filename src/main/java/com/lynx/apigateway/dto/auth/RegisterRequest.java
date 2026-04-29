package com.lynx.apigateway.dto.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegisterRequest(
        @NotBlank(message = "Email cannot be empty.")
        @Email(message = "The email address is invalid.")
        @JsonProperty("email")
        String email,

        @NotBlank(message = "Password cannot be empty.")
        @Size(min = 8, message = "Password must be at least 8 characters.")
        @JsonProperty("password")
        String password,

        @NotBlank(message = "First name cannot be empty.")
        @JsonProperty("first_name")
        String firstName,

        @NotBlank(message = "Last name cannot be empty.")
        @JsonProperty("last_name")
        String lastName,

        @NotBlank(message = "Username cannot be empty.")
        @JsonProperty("username")
        String username,

        @NotNull(message = "Date of birth cannot be null.")
        @Past(message = "Date of birth must be in the past.")
        @JsonProperty("date_of_birth")
        LocalDate dateOfBirth
) {
}