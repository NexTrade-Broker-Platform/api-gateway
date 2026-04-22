package com.lynx.apigateway.service;

import com.lynx.apigateway.dto.response.PortfolioResponse;

import java.util.UUID;

public interface PortfolioFacade {
    PortfolioResponse getPortfolio(UUID userId);
}