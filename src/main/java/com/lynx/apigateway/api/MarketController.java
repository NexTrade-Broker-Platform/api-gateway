package com.lynx.apigateway.api;

import com.lynx.apigateway.dto.response.StockDetailsResponse;
import com.lynx.apigateway.dto.response.StockListResponse;
import com.lynx.apigateway.service.MarketFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/market")
public class MarketController {

    private final MarketFacade marketFacade;

    public MarketController(MarketFacade marketFacade) {
        this.marketFacade = marketFacade;
    }

    @GetMapping("/stocks")
    public ResponseEntity<StockListResponse> getStocks(
            @RequestParam(required = false) String sector,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit
    ) {
        return ResponseEntity.ok(marketFacade.getStocks(sector, page, limit));
    }

    @GetMapping("/stocks/{ticker}")
    public ResponseEntity<StockDetailsResponse> getStockByTicker(@PathVariable String ticker) {
        return ResponseEntity.ok(marketFacade.getStockByTicker(ticker));
    }
}