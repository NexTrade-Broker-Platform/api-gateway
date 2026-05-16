package com.lynx.apigateway.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.lynx.apigateway.dto.order.CandleDto;

import java.util.List;

public record StockHistoryResponse(
        @JsonProperty("ticker")
        String ticker,

        @JsonProperty("from")
        String from,

        @JsonProperty("to")
        String to,

        @JsonProperty("chart_data")
        List<CandleDto> chartData
) {
}
