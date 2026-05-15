package com.lynx.apigateway.service.impl;

import com.lynx.apigateway.dto.response.MarketEventsResponse;
import com.lynx.apigateway.dto.response.OptionsListResponse;
import com.lynx.apigateway.dto.response.StockDetailsResponse;
import com.lynx.apigateway.dto.response.StockListResponse;
import com.lynx.apigateway.error.NotFoundException;
import com.lynx.apigateway.service.MarketFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;
import java.util.Objects;

@Service
@Primary
@Slf4j
public class ExchangeMarketFacade implements MarketFacade {

    private final RestClient restClient;

    @Value("${services.notification.url}")
    private String notificationUrl;

    public ExchangeMarketFacade(RestClient.Builder restClientBuilder) {
        this.restClient = restClientBuilder.build();
    }

    @Override
    public StockListResponse getStocks(String sector, Integer page, Integer limit) {
        try {
            StockListResponse response = restClient.get()
                    .uri(notificationUrl + "/internal/market/stocks")
                    .retrieve()
                    .body(StockListResponse.class);
            return response != null ? response : new StockListResponse(List.of());
        } catch (Exception e) {
            log.error("Failed to fetch stocks from notification service: {}", e.getMessage());
            return new StockListResponse(List.of());
        }
    }

    @Override
    public StockDetailsResponse getStockByTicker(String ticker) {
        try {
            StockDetailsResponse response = restClient.get()
                    .uri(notificationUrl + "/internal/market/stocks/" + ticker)
                    .retrieve()
                    .body(StockDetailsResponse.class);
            if (response == null) throw new NotFoundException("Stock ticker " + ticker + " not found.");
            return response;
        } catch (NotFoundException e) {
            throw e;
        } catch (Exception e) {
            log.error("Failed to fetch stock {} from notification service: {}", ticker, e.getMessage());
            throw new NotFoundException("Stock ticker " + ticker + " not found.");
        }
    }

    @Override
    public OptionsListResponse getOptions() {
        try {
            OptionsListResponse response = restClient.get()
                    .uri(notificationUrl + "/internal/market/options")
                    .retrieve()
                    .body(OptionsListResponse.class);
            return response != null ? response : new OptionsListResponse(List.of());
        } catch (Exception e) {
            log.error("Failed to fetch options from notification service: {}", e.getMessage());
            return new OptionsListResponse(List.of());
        }
    }

    @Override
    public MarketEventsResponse getMarketEvents() {
        try {
            MarketEventsResponse response = restClient.get()
                    .uri(notificationUrl + "/internal/market/events")
                    .retrieve()
                    .body(MarketEventsResponse.class);
            return response != null ? response : new MarketEventsResponse(List.of());
        } catch (Exception e) {
            log.error("Failed to fetch market events from notification service: {}", e.getMessage());
            return new MarketEventsResponse(List.of());
        }
    }
}
