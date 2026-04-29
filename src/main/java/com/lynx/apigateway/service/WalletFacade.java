package com.lynx.apigateway.service;

import com.lynx.apigateway.dto.request.DepositRequest;
import com.lynx.apigateway.dto.request.WithdrawRequest;
import com.lynx.apigateway.dto.response.DepositResponse;
import com.lynx.apigateway.dto.response.WalletBalanceResponse;
import com.lynx.apigateway.dto.response.WithdrawResponse;

import java.util.UUID;

public interface WalletFacade {
    DepositResponse deposit(UUID userId, DepositRequest request);
    WithdrawResponse withdraw(UUID userId, WithdrawRequest request);
    WalletBalanceResponse getBalance(UUID userId, String currency);
//    WalletTransactionsPageResponse getTransactions(UUID userId, int page, int limit);
}