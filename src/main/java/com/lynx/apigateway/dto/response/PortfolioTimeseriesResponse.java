package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record PortfolioTimeseriesResponse(
        @JsonProperty("points")
        List<PortfolioTimeseriesPointDto> points
) {
}
