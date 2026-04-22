package com.lynx.apigateway.api;

import com.lynx.apigateway.api.dto.request.LoginRequest;
import com.lynx.apigateway.api.dto.request.RegisterRequest;
import com.lynx.apigateway.api.dto.response.AuthResponse;
import com.lynx.apigateway.api.dto.response.MessageResponse;
import com.lynx.apigateway.service.AuthFacade;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class AuthController {

    private final AuthFacade authFacade;

    public AuthController(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authFacade.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authFacade.login(request));
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout() {
        return ResponseEntity.ok(new MessageResponse("User logged out successfully"));
    }
}