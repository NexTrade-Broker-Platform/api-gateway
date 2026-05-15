package com.lynx.apigateway.dto.response;

import java.util.List;

public record MarketEventsResponse(List<MarketEventDto> events) {}
