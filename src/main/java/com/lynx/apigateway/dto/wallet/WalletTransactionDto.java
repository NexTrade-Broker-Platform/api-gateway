package com.lynx.apigateway.dto.wallet;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record WalletTransactionDto(
        UUID id,
        UUID walletId,
        UUID referenceId,
        String transactionType,
        BigDecimal amount,
        LocalDateTime createdAt
) {
}