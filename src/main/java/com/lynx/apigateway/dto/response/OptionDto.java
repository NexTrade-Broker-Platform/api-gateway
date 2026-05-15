package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record OptionDto(
        @JsonProperty("option_id")        String optionId,
        @JsonProperty("underlying_ticker") String underlyingTicker,
        @JsonProperty("option_type")       String optionType,
        @JsonProperty("strike_price")      double strikePrice,
        @JsonProperty("expiry_time")       String expiryTime,
        @JsonProperty("premium")           double premium,
        @JsonProperty("is_active")         boolean isActive
) {}
