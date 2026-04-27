package com.lynx.apigateway.dto.response;

import java.util.List;

public record WalletTransactionsPageResponse(
        List<WalletTransactionDto> transactions,
        PaginationDto pagination
) {
}