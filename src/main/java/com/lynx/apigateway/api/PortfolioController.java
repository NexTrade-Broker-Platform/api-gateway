package com.lynx.apigateway.api;

import com.lynx.apigateway.dto.response.PortfolioResponse;
import com.lynx.apigateway.service.PortfolioFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class PortfolioController {

    private final PortfolioFacade portfolioFacade;

    public PortfolioController(PortfolioFacade portfolioFacade) {
        this.portfolioFacade = portfolioFacade;
    }

    @GetMapping("/api/portfolio")
    public ResponseEntity<PortfolioResponse> getPortfolio(Authentication authentication) {
        UUID userId = UUID.fromString(authentication.getName());
        return ResponseEntity.ok(portfolioFacade.getPortfolio(userId));
    }
}