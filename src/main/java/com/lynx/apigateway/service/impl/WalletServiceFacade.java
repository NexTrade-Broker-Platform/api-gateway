package com.lynx.apigateway.service.impl;

import com.lynx.apigateway.dto.wallet.DepositRequest;
import com.lynx.apigateway.dto.wallet.WithdrawRequest;
import com.lynx.apigateway.dto.wallet.DepositResponse;
import com.lynx.apigateway.dto.wallet.WalletBalanceResponse;
import com.lynx.apigateway.dto.wallet.WalletDto;
import com.lynx.apigateway.dto.wallet.WithdrawResponse;
import com.lynx.apigateway.service.WalletFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WalletServiceFacade implements WalletFacade {

    private final RestClient.Builder restClientBuilder;

    @Value("${services.wallet.url}")
    private String walletServiceUrl;

    @Override
    public DepositResponse deposit(UUID userId, DepositRequest request) {
        return restClientBuilder.build()
                .post()
                .uri(walletServiceUrl + "/funds/deposit")
                .header("X-User-Id", userId.toString())
                .body(request)
                .retrieve()
                .body(DepositResponse.class);
    }

    @Override
    public WithdrawResponse withdraw(UUID userId, WithdrawRequest request) {
        return restClientBuilder.build()
                .post()
                .uri(walletServiceUrl + "/funds/withdraw")
                .header("X-User-Id", userId.toString())
                .body(request)
                .retrieve()
                .body(WithdrawResponse.class);
    }

    @Override
    public WalletBalanceResponse getBalance(UUID userId, String currency) {
        WalletDto wallet = restClientBuilder.build()
                .get()
                .uri(walletServiceUrl + "/funds?currency={currency}", currency)
                .header("X-User-Id", userId.toString())
                .retrieve()
                .body(WalletDto.class);

        return new WalletBalanceResponse(wallet);
    }
}