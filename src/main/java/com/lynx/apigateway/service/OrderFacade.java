package com.lynx.apigateway.service;

import com.lynx.apigateway.dto.order.PlaceOrderRequest;
import com.lynx.apigateway.dto.order.CancelOrderResponse;
import com.lynx.apigateway.dto.order.OrderHistoryResponse;
import com.lynx.apigateway.dto.order.PlaceOrderResponse;

import java.util.UUID;

public interface OrderFacade {
    PlaceOrderResponse placeOrder(UUID userId, PlaceOrderRequest request);
    OrderHistoryResponse getOrders(UUID userId, String status, Integer page, Integer limit);
    CancelOrderResponse cancelOrder(UUID userId, String orderId);
}