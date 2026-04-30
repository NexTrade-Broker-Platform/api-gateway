package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lynx.apigateway.dto.order.CandleDto;

import java.util.List;

public record StockDetailsResponse(
        @JsonProperty("stock")
        StockDto stock,

        @JsonProperty("chart_data")
        List<CandleDto> chartData
) {
}