package com.lynx.apigateway.service.stub;

import com.lynx.apigateway.dto.order.CandleDto;
import com.lynx.apigateway.dto.response.StockDetailsResponse;
import com.lynx.apigateway.dto.response.StockDto;
import com.lynx.apigateway.dto.response.StockListResponse;
import com.lynx.apigateway.error.NotFoundException;
import com.lynx.apigateway.error.ValidationException;
import com.lynx.apigateway.service.MarketFacade;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.Set;

@Service
public class StubMarketFacade implements MarketFacade {

    private static final Set<String> VALID_SECTORS = Set.of("TECH", "FINANCE", "ENERGY");

    @Override
    public StockListResponse getStocks(String sector, Integer page, Integer limit) {
        int safePage = page == null ? 0 : page;
        int safeLimit = limit == null ? 10 : limit;

        if (safePage < 0) {
            throw new ValidationException("Page number must be strictly greater than 0.");
        }
        if (safeLimit < 5 || safeLimit > 50) {
            throw new ValidationException("Limit must be between 1 and 100.");
        }
        if (sector != null && !VALID_SECTORS.contains(sector.toUpperCase(Locale.ROOT))) {
            throw new ValidationException("The specified sector does not exist.");
        }

        List<StockDto> stocks = List.of(
                new StockDto(
                        "ARKA",
                        "Arkadia Technologies",
                        "Tech",
                        new BigDecimal("142.50"),
                        new BigDecimal("140.00"),
                        new BigDecimal("145.20"),
                        new BigDecimal("139.50"),
                        1_250_400L,
                        LocalDateTime.of(2026, 4, 10, 9, 0, 0)
                ),
                new StockDto(
                        "NXGY",
                        "NextGen Energy",
                        "Energy",
                        new BigDecimal("45.20"),
                        new BigDecimal("46.10"),
                        new BigDecimal("46.50"),
                        new BigDecimal("44.80"),
                        890_000L,
                        LocalDateTime.of(2026, 4, 12, 9, 0, 0)
                )
        );

        return new StockListResponse(stocks);
    }

    @Override
    public StockDetailsResponse getStockByTicker(String ticker) {
        if ("ARKA".equalsIgnoreCase(ticker)) {
            StockDto stock = new StockDto(
                    "ARKA",
                    "Arkadia Technologies",
                    "Tech",
                    new BigDecimal("142.50"),
                    new BigDecimal("140.00"),
                    new BigDecimal("145.20"),
                    new BigDecimal("139.50"),
                    1_250_400L,
                    LocalDateTime.of(2026, 4, 10, 9, 0, 0)
            );

            List<CandleDto> chartData = List.of(
                    new CandleDto(
                            LocalDateTime.of(2026, 4, 21, 9, 30, 0),
                            new BigDecimal("140.00"),
                            new BigDecimal("141.50"),
                            new BigDecimal("139.50"),
                            new BigDecimal("141.00"),
                            150_000L
                    ),
                    new CandleDto(
                            LocalDateTime.of(2026, 4, 21, 10, 0, 0),
                            new BigDecimal("141.00"),
                            new BigDecimal("142.20"),
                            new BigDecimal("140.80"),
                            new BigDecimal("142.00"),
                            220_000L
                    ),
                    new CandleDto(
                            LocalDateTime.of(2026, 4, 21, 10, 30, 0),
                            new BigDecimal("142.00"),
                            new BigDecimal("145.20"),
                            new BigDecimal("141.50"),
                            new BigDecimal("142.50"),
                            310_400L
                    )
            );
            return new StockDetailsResponse(stock, chartData);
        } else if ("NXGY".equalsIgnoreCase(ticker) || "NGXY".equalsIgnoreCase(ticker)) {
            StockDto stock = new StockDto(
                    "NXGY",
                    "NextGen Energy",
                    "Energy",
                    new BigDecimal("45.20"),
                    new BigDecimal("46.10"),
                    new BigDecimal("46.50"),
                    new BigDecimal("44.80"),
                    890_000L,
                    LocalDateTime.of(2026, 4, 12, 9, 0, 0)
            );

            List<CandleDto> chartData = List.of(
                    new CandleDto(
                            LocalDateTime.of(2026, 4, 21, 9, 30, 0),
                            new BigDecimal("44.80"),
                            new BigDecimal("45.50"),
                            new BigDecimal("44.50"),
                            new BigDecimal("45.00"),
                            100_000L
                    ),
                    new CandleDto(
                            LocalDateTime.of(2026, 4, 21, 10, 0, 0),
                            new BigDecimal("45.00"),
                            new BigDecimal("46.10"),
                            new BigDecimal("44.80"),
                            new BigDecimal("45.20"),
                            150_000L
                    )
            );
            return new StockDetailsResponse(stock, chartData);
        } else {
            throw new NotFoundException("Stock ticker " + ticker + " not found.");
        }
    }
}
