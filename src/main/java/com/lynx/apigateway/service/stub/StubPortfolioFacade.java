package com.lynx.apigateway.service.stub;

import com.lynx.apigateway.dto.response.CashBalanceDto;
import com.lynx.apigateway.dto.response.HoldingDto;
import com.lynx.apigateway.dto.response.PortfolioResponse;
import com.lynx.apigateway.service.PortfolioFacade;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class StubPortfolioFacade implements PortfolioFacade {

    @Override
    public PortfolioResponse getPortfolio(UUID userId) {
        List<CashBalanceDto> cashBalances = List.of(
                new CashBalanceDto(
                        "USD",
                        new BigDecimal("1500.00"),
                        new BigDecimal("500.00")
                )
        );

        List<HoldingDto> holdings = List.of(
                new HoldingDto(
                        "AAPL",
                        "STOCK",
                        15,
                        new BigDecimal("145.50")
                ),
                new HoldingDto(
                        "TSLA260417C200",
                        "OPTION",
                        2,
                        new BigDecimal("5.20")
                )
        );

        return new PortfolioResponse(cashBalances, holdings);
    }
}