package com.lynx.apigateway.service;

import com.lynx.apigateway.dto.request.PlaceOrderRequest;
import com.lynx.apigateway.dto.response.CancelOrderResponse;
import com.lynx.apigateway.dto.response.OrderHistoryResponse;
import com.lynx.apigateway.dto.response.PlaceOrderResponse;

import java.util.UUID;

public interface OrderFacade {
    PlaceOrderResponse placeOrder(UUID userId, PlaceOrderRequest request);
    OrderHistoryResponse getOrders(UUID userId, String status, Integer page, Integer limit);
    CancelOrderResponse cancelOrder(UUID userId, String orderId);
}