package com.lynx.apigateway.api;

import com.lynx.apigateway.dto.response.PortfolioResponse;
import com.lynx.apigateway.dto.response.PortfolioTimeseriesResponse;
import com.lynx.apigateway.service.PortfolioFacade;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@Validated
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

    @GetMapping("/api/portfolio/timeseries")
    public ResponseEntity<PortfolioTimeseriesResponse> getPortfolioTimeseries(
            Authentication authentication,
            @RequestParam(defaultValue = "30")
            @Min(value = 7, message = "days must be at least 7.")
            @Max(value = 365, message = "days must be at most 365.")
            int days
    ) {
        UUID userId = UUID.fromString(authentication.getName());
        return ResponseEntity.ok(portfolioFacade.getPortfolioTimeseries(userId, days));
    }
}
