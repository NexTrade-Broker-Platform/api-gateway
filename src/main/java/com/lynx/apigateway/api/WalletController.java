package com.lynx.apigateway.api;

import com.lynx.apigateway.dto.request.DepositRequest;
import com.lynx.apigateway.dto.request.WithdrawRequest;
import com.lynx.apigateway.dto.response.DepositResponse;
import com.lynx.apigateway.dto.response.WithdrawResponse;
import com.lynx.apigateway.service.WalletFacade;
import com.lynx.apigateway.dto.response.WalletBalanceResponse;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
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
    public ResponseEntity<WithdrawResponse> withdraw(
            @Valid @RequestBody WithdrawRequest request,
            Authentication authentication
    ) {
        UUID userId = UUID.fromString(authentication.getName());
        return ResponseEntity.ok(walletFacade.withdraw(userId, request));
    }

    @GetMapping("/balance")
    public ResponseEntity<WalletBalanceResponse> getBalance(
            @RequestParam(defaultValue = "USD")
            @Pattern(regexp = "^[A-Za-z]{3}$", message = "Currency must be a valid 3-letter ISO code.")
            String currency,
            Authentication authentication
    ) {
        UUID userId = UUID.fromString(authentication.getName());
        return ResponseEntity.ok(walletFacade.getBalance(userId, currency.toUpperCase()));
    }


    // ====================



//    @GetMapping("/transactions")
//    public ResponseEntity<WalletTransactionsPageResponse> getTransactions(
//            @RequestParam(defaultValue = "0")
//            @Min(value = 0, message = "Page must be greater than or equal to 0.")
//            int page,
//            @RequestParam(defaultValue = "10")
//            @Min(value = 1, message = "Limit must be at least 1.")
//            @Max(value = 50, message = "Limit must be at most 50.")
//            int limit,
//            Authentication authentication
//    ) {
//        UUID userId = UUID.fromString(authentication.getName());
//        return ResponseEntity.ok(walletFacade.getTransactions(userId, page, limit));
//    }
}