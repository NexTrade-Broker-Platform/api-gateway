package com.lynx.apigateway.service.impl;

import com.lynx.apigateway.dto.response.HoldingDto;
import com.lynx.apigateway.dto.response.PortfolioResponse;
import com.lynx.apigateway.dto.wallet.CashBalanceDto;
import com.lynx.apigateway.dto.wallet.WalletBalanceResponse;
import com.lynx.apigateway.dto.wallet.WalletDto;
import com.lynx.apigateway.service.PortfolioFacade;
import com.lynx.apigateway.service.WalletFacade;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.UUID;

@Service
public class PortfolioServiceFacade implements PortfolioFacade {

    private static final Logger log = LoggerFactory.getLogger(PortfolioServiceFacade.class);

    private final RestClient restClient;
    private final String portfolioServiceUrl;
    private final WalletFacade walletFacade;

    public PortfolioServiceFacade(
            RestClient.Builder restClientBuilder,
            @Value("${services.portfolio.url}")String portfolioServiceUrl,
            WalletFacade walletFacade
    ){
        this.restClient = restClientBuilder.build();
        this.portfolioServiceUrl = portfolioServiceUrl;
        this.walletFacade = walletFacade;
    }

    @Override
    public PortfolioResponse getPortfolio(UUID userId) {
        List<HoldingDto> holdings = restClient.get()
                .uri(portfolioServiceUrl + "/portfolio")
                .header("X-User-Id", userId.toString())
                .retrieve()
                .body(new ParameterizedTypeReference<>() {});

        if (holdings == null) {
            holdings = List.of();
        }

        for (HoldingDto holding : holdings)
            log.debug("Holding fetched: ticker={}, quantity={}, averageCost={}", holding.ticker(), holding.quantity(), holding.averageCost());

        WalletBalanceResponse balanceResponse = walletFacade.getBalance(userId, "USD");
        WalletDto wallet = balanceResponse.wallet();
        
        CashBalanceDto cashBalance = new CashBalanceDto(
                wallet.currency(),
                wallet.availableBalance(),
                wallet.reservedBalance()
        );

        return new PortfolioResponse(List.of(cashBalance), holdings);
    }
}
