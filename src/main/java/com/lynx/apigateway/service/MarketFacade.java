package com.lynx.apigateway.service;

import com.lynx.apigateway.dto.response.MarketEventsResponse;
import com.lynx.apigateway.dto.response.OptionsListResponse;
import com.lynx.apigateway.dto.response.StockDetailsResponse;
import com.lynx.apigateway.dto.response.StockListResponse;

public interface MarketFacade {
    StockListResponse getStocks(String sector, Integer page, Integer limit);
    StockDetailsResponse getStockByTicker(String ticker);
    OptionsListResponse getOptions();
    MarketEventsResponse getMarketEvents();
}