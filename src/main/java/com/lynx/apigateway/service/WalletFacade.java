package com.lynx.apigateway.service;

import com.lynx.apigateway.dto.request.DepositRequest;
import com.lynx.apigateway.dto.request.WithdrawRequest;
import com.lynx.apigateway.dto.response.DepositResponse;

import java.util.UUID;

public interface WalletFacade {
    DepositResponse deposit(UUID userId, DepositRequest request);
    DepositResponse withdraw(UUID userId, WithdrawRequest request);
}