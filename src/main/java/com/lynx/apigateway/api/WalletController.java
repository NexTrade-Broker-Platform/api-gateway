package com.lynx.apigateway.api;

import com.lynx.apigateway.dto.request.DepositRequest;
import com.lynx.apigateway.dto.request.WithdrawRequest;
import com.lynx.apigateway.dto.response.DepositResponse;
import com.lynx.apigateway.service.WalletFacade;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/funds")
public class WalletController {

    private final WalletFacade walletFacade;

    public WalletController(WalletFacade walletFacade) {
        this.walletFacade = walletFacade;
    }

    @PostMapping("/deposit")
    public ResponseEntity<DepositResponse> deposit(
            @Valid @RequestBody DepositRequest request,
            Authentication authentication
    ) {
        UUID userId = UUID.fromString(authentication.getName());
        return ResponseEntity.ok(walletFacade.deposit(userId, request));
    }

    @PostMapping("/withdraw")
    public ResponseEntity<DepositResponse> withdraw(
            @Valid @RequestBody WithdrawRequest request,
            Authentication authentication
    ) {
        UUID userId = UUID.fromString(authentication.getName());
        return ResponseEntity.ok(walletFacade.withdraw(userId, request));
    }
}