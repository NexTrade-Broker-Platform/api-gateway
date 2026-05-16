package com.lynx.apigateway.service;

import com.lynx.apigateway.dto.response.PortfolioResponse;
import com.lynx.apigateway.dto.response.PortfolioTimeseriesResponse;

import java.util.UUID;

public interface PortfolioFacade {
    PortfolioResponse getPortfolio(UUID userId);
    PortfolioTimeseriesResponse getPortfolioTimeseries(UUID userId, int days);
}
