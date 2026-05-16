package com.lynx.apigateway.api;

import com.lynx.apigateway.dto.response.MarketEventsResponse;
import com.lynx.apigateway.dto.response.MarketStatusResponse;
import com.lynx.apigateway.dto.response.OptionsListResponse;
import com.lynx.apigateway.dto.response.StockDetailsResponse;
import com.lynx.apigateway.dto.response.StockHistoryResponse;
import com.lynx.apigateway.dto.response.StockListResponse;
import com.lynx.apigateway.error.ValidationException;
import com.lynx.apigateway.service.MarketFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    @GetMapping("/stocks/{ticker}/history")
    public ResponseEntity<StockHistoryResponse> getStockHistory(
            @PathVariable String ticker,
            @RequestParam String from,
            @RequestParam String to
    ) {
        LocalDate fromDate = parseDateParam("from", from);
        LocalDate toDate = parseDateParam("to", to);
        return ResponseEntity.ok(marketFacade.getStockHistory(ticker, fromDate, toDate));
    }

    @GetMapping("/options")
    public ResponseEntity<OptionsListResponse> getOptions() {
        return ResponseEntity.ok(marketFacade.getOptions());
    }

    @GetMapping("/status")
    public ResponseEntity<MarketStatusResponse> getMarketStatus() {
        return ResponseEntity.ok(marketFacade.getMarketStatus());
    }

    @GetMapping("/events")
    public ResponseEntity<MarketEventsResponse> getMarketEvents() {
        return ResponseEntity.ok(marketFacade.getMarketEvents());
    }

    private LocalDate parseDateParam(String name, String value) {
        try {
            return LocalDate.parse(value);
        } catch (DateTimeParseException ex) {
            throw new ValidationException(name + " must use YYYY-MM-DD format.");
        }
    }
}
