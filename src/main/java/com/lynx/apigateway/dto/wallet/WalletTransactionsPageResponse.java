package com.lynx.apigateway.dto.wallet;

import com.lynx.apigateway.dto.response.PaginationDto;

import java.util.List;

public record WalletTransactionsPageResponse(
        List<WalletTransactionDto> transactions,
        PaginationDto pagination
) {
}