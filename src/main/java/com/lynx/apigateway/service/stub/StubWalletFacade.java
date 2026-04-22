package com.lynx.apigateway.service.stub;

import com.lynx.apigateway.dto.request.DepositRequest;
import com.lynx.apigateway.dto.request.WithdrawRequest;
import com.lynx.apigateway.dto.response.DepositResponse;
import com.lynx.apigateway.dto.response.WalletDto;
import com.lynx.apigateway.dto.response.WalletTransactionDto;
import com.lynx.apigateway.error.InsufficientFundsException;
import com.lynx.apigateway.service.WalletFacade;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StubWalletFacade implements WalletFacade {

    private static final UUID WALLET_ID = UUID.fromString("880e8400-e29b-41d4-a716-446655440111");

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
                LocalDateTime.of(2026, 4, 10, 9, 0, 0),
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
        BigDecimal availableBalance = new BigDecimal("1500.00");

        if (request.amount().compareTo(availableBalance) > 0) {
            throw new InsufficientFundsException("Not enough available balance to withdraw.");
        }

        LocalDateTime now = LocalDateTime.now();
        BigDecimal newBalance = availableBalance.subtract(request.amount());

        WalletDto wallet = new WalletDto(
                WALLET_ID,
                userId,
                request.currency().toUpperCase(),
                newBalance,
                BigDecimal.ZERO,
                now,
                LocalDateTime.of(2026, 4, 10, 9, 0, 0),
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
}