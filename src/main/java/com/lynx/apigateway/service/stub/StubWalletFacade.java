package com.lynx.apigateway.service.stub;

import com.lynx.apigateway.dto.request.DepositRequest;
import com.lynx.apigateway.dto.request.WithdrawRequest;
import com.lynx.apigateway.dto.response.DepositResponse;
import com.lynx.apigateway.dto.response.PaginationDto;
import com.lynx.apigateway.dto.response.WalletBalanceResponse;
import com.lynx.apigateway.dto.response.WalletDto;
import com.lynx.apigateway.dto.response.WalletTransactionDto;
import com.lynx.apigateway.dto.response.WalletTransactionsPageResponse;
import com.lynx.apigateway.error.InsufficientFundsException;
import com.lynx.apigateway.service.WalletFacade;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class StubWalletFacade implements WalletFacade {

    private static final UUID WALLET_ID = UUID.fromString("880e8400-e29b-41d4-a716-446655440111");
    private static final BigDecimal AVAILABLE_BALANCE = new BigDecimal("1500.00");
    private static final BigDecimal RESERVED_BALANCE = new BigDecimal("0.00");
    private static final LocalDateTime CREATED_AT = LocalDateTime.of(2026, 4, 10, 9, 0, 0);

    @Override
    public DepositResponse deposit(UUID userId, DepositRequest request) {
        LocalDateTime now = LocalDateTime.now();

        WalletDto wallet = new WalletDto(
                WALLET_ID,
                userId,
                request.currency().toUpperCase(),
                request.amount(),
                BigDecimal.ZERO,
                now,
                CREATED_AT,
                true
        );

        WalletTransactionDto transaction = new WalletTransactionDto(
                UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                WALLET_ID,
                null,
                "DEPOSIT",
                request.amount(),
                now
        );

        return new DepositResponse("Deposit successful", wallet, transaction);
    }

    @Override
    public DepositResponse withdraw(UUID userId, WithdrawRequest request) {
        if (request.amount().compareTo(AVAILABLE_BALANCE) > 0) {
            throw new InsufficientFundsException("Not enough available balance to withdraw.");
        }

        LocalDateTime now = LocalDateTime.now();
        BigDecimal newBalance = AVAILABLE_BALANCE.subtract(request.amount());

        WalletDto wallet = new WalletDto(
                WALLET_ID,
                userId,
                request.currency().toUpperCase(),
                newBalance,
                BigDecimal.ZERO,
                now,
                CREATED_AT,
                true
        );

        WalletTransactionDto transaction = new WalletTransactionDto(
                UUID.fromString("223e4567-e89b-12d3-a456-426614174000"),
                WALLET_ID,
                null,
                "WITHDRAWAL",
                request.amount(),
                now
        );

        return new DepositResponse("Withdrawal successful", wallet, transaction);
    }

    @Override
    public WalletBalanceResponse getBalance(UUID userId, String currency) {
        LocalDateTime now = LocalDateTime.now();

        WalletDto wallet = new WalletDto(
                WALLET_ID,
                userId,
                currency.toUpperCase(),
                AVAILABLE_BALANCE,
                RESERVED_BALANCE,
                now,
                CREATED_AT,
                true
        );

        return new WalletBalanceResponse(wallet);
    }

    @Override
    public WalletTransactionsPageResponse getTransactions(UUID userId, int page, int limit) {
        List<WalletTransactionDto> allTransactions = List.of(
                new WalletTransactionDto(
                        UUID.fromString("123e4567-e89b-12d3-a456-426614174000"),
                        WALLET_ID,
                        null,
                        "DEPOSIT",
                        new BigDecimal("1500.00"),
                        LocalDateTime.of(2026, 4, 16, 15, 45, 0)
                ),
                new WalletTransactionDto(
                        UUID.fromString("223e4567-e89b-12d3-a456-426614174000"),
                        WALLET_ID,
                        null,
                        "WITHDRAWAL",
                        new BigDecimal("200.00"),
                        LocalDateTime.of(2026, 4, 17, 11, 30, 0)
                ),
                new WalletTransactionDto(
                        UUID.fromString("323e4567-e89b-12d3-a456-426614174000"),
                        WALLET_ID,
                        UUID.fromString("999e4567-e89b-12d3-a456-426614174999"),
                        "ORDER_HOLD",
                        new BigDecimal("500.00"),
                        LocalDateTime.of(2026, 4, 18, 10, 15, 0)
                )
        );

        int totalRecords = allTransactions.size();
        int totalPages = (int) Math.ceil((double) totalRecords / limit);

        int fromIndex = page * limit;
        int toIndex = Math.min(fromIndex + limit, totalRecords);

        List<WalletTransactionDto> pageContent;

        if (fromIndex >= totalRecords) {
            pageContent = List.of();
        } else {
            pageContent = allTransactions.subList(fromIndex, toIndex);
        }

        PaginationDto pagination = new PaginationDto(
                totalRecords,
                page,
                totalPages,
                limit
        );

        return new WalletTransactionsPageResponse(pageContent, pagination);
    }
}