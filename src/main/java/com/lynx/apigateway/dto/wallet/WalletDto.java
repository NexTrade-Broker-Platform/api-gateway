package com.lynx.apigateway.dto.wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record WalletDto(
        UUID id,
        UUID userId,
        String currency,
        BigDecimal availableBalance,
        BigDecimal reservedBalance,
        LocalDateTime updatedAt,
        LocalDateTime createdAt,
        boolean active
) {
}