package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MarketEventDto(
        @JsonProperty("event_id")       String eventId,
        @JsonProperty("event_type")     String eventType,
        @JsonProperty("headline")       String headline,
        @JsonProperty("scope")          String scope,
        @JsonProperty("target")         String target,
        @JsonProperty("magnitude")      double magnitude,
        @JsonProperty("duration_ticks") int durationTicks,
        @JsonProperty("market_time")    String marketTime
) {}
