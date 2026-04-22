package com.lynx.apigateway.api;

import com.lynx.apigateway.dto.request.PlaceOrderRequest;
import com.lynx.apigateway.dto.response.CancelOrderResponse;
import com.lynx.apigateway.dto.response.OrderHistoryResponse;
import com.lynx.apigateway.dto.response.PlaceOrderResponse;
import com.lynx.apigateway.service.OrderFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderFacade orderFacade;

    public OrderController(OrderFacade orderFacade) {
        this.orderFacade = orderFacade;
    }

    @PostMapping
    public ResponseEntity<PlaceOrderResponse> placeOrder(
            @Valid @RequestBody PlaceOrderRequest request,
            Authentication authentication
    ) {
        UUID userId = UUID.fromString(authentication.getName());
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(orderFacade.placeOrder(userId, request));
    }

    @GetMapping
    public ResponseEntity<OrderHistoryResponse> getOrders(
            @RequestParam(required = false) String status,
            @RequestParam(required = false) Integer page,
            @RequestParam(required = false) Integer limit,
            Authentication authentication
    ) {
        UUID userId = UUID.fromString(authentication.getName());
        return ResponseEntity.ok(orderFacade.getOrders(userId, status, page, limit));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CancelOrderResponse> cancelOrder(
            @PathVariable String id,
            Authentication authentication
    ) {
        UUID userId = UUID.fromString(authentication.getName());
        return ResponseEntity.ok(orderFacade.cancelOrder(userId, id));
    }
}