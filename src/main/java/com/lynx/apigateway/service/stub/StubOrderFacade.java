package com.lynx.apigateway.service.stub;

import com.lynx.apigateway.dto.request.PlaceOrderRequest;
import com.lynx.apigateway.dto.response.*;
import com.lynx.apigateway.error.NotFoundException;
import com.lynx.apigateway.error.ValidationException;
import com.lynx.apigateway.service.OrderFacade;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class StubOrderFacade implements OrderFacade {

    private static final String PLATFORM_ID = "team3-123132123123324";
    private static final Set<String> VALID_STATUSES = Set.of(
            "PENDING", "PARTIALLY_FILLED", "FILLED", "REJECTED", "CANCELLED", "EXPIRED"
    );

    @Override
    public PlaceOrderResponse placeOrder(UUID userId, PlaceOrderRequest request) {
        validatePlaceOrder(request);

        LocalDateTime now = LocalDateTime.of(2026, 4, 17, 10, 30, 0);

        OrderDto order = new OrderDto(
                "ord-9999-8888-7777",
                PLATFORM_ID,
                userId.toString(),
                request.instrumentType(),
                request.instrumentId(),
                request.orderType(),
                request.side(),
                request.quantity(),
                request.limitPrice(),
                "PENDING",
                0,
                null,
                BigDecimal.ZERO,
                now,
                now,
                now.plusDays(3)
        );

        return new PlaceOrderResponse("Order accepted and routed to exchange.", order);
    }

    @Override
    public OrderHistoryResponse getOrders(UUID userId, String status, Integer page, Integer limit) {
        int safePage = page == null ? 0 : page;
        int safeLimit = limit == null ? 10 : limit;

        if (safePage < 0) {
            throw new ValidationException("Page number must be greater than or equal to 0.");
        }
        if (safeLimit < 5 || safeLimit > 50) {
            throw new ValidationException("Limit must be between 5 and 50.");
        }
        if (status != null && !VALID_STATUSES.contains(status.toUpperCase())) {
            throw new ValidationException("The selected status does not exist.");
        }

        OrderDto order = new OrderDto(
                "ord-9999-8888-7777",
                PLATFORM_ID,
                userId.toString(),
                "STOCK",
                "AAPL",
                "LIMIT",
                "BUY",
                10,
                new BigDecimal("145.50"),
                "PENDING",
                0,
                null,
                BigDecimal.ZERO,
                LocalDateTime.of(2026, 4, 17, 10, 30, 0),
                LocalDateTime.of(2026, 4, 17, 10, 30, 0),
                LocalDateTime.of(2026, 4, 20, 10, 30, 0)
        );

        PaginationDto pagination = new PaginationDto(
                45,
                safePage,
                5,
                safeLimit
        );

        return new OrderHistoryResponse(List.of(order), pagination);
    }

    @Override
    public CancelOrderResponse cancelOrder(UUID userId, String orderId) {
        if ("missing-order".equals(orderId)) {
            throw new NotFoundException("The requested order ID does not exist.");
        }

        if ("filled-order".equals(orderId)) {
            throw new ValidationException("Cannot cancel this order because it has already been filled or is currently processing.");
        }

        CancelOrderDto order = new CancelOrderDto(
                orderId,
                "CANCELLED",
                LocalDateTime.of(2026, 4, 17, 17, 45, 0)
        );

        return new CancelOrderResponse("Order successfully cancelled.", order);
    }

    private void validatePlaceOrder(PlaceOrderRequest request) {
        if ("LIMIT".equalsIgnoreCase(request.orderType()) && request.limitPrice() == null) {
            throw new ValidationException("limit_price is required when order_type is LIMIT.");
        }
    }
}