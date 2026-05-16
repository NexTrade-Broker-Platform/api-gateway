package com.lynx.apigateway.service;

import com.lynx.apigateway.dto.response.MarketEventsResponse;
import com.lynx.apigateway.dto.response.MarketStatusResponse;
import com.lynx.apigateway.dto.response.OptionsListResponse;
import com.lynx.apigateway.dto.response.StockDetailsResponse;
import com.lynx.apigateway.dto.response.StockHistoryResponse;
import com.lynx.apigateway.dto.response.StockListResponse;

import java.time.LocalDate;

public interface MarketFacade {
    StockListResponse getStocks(String sector, Integer page, Integer limit);
    StockDetailsResponse getStockByTicker(String ticker);
    StockHistoryResponse getStockHistory(String ticker, LocalDate from, LocalDate to);
    MarketStatusResponse getMarketStatus();
    OptionsListResponse getOptions();
    MarketEventsResponse getMarketEvents();
}
