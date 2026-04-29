package com.lynx.apigateway.api;

import com.lynx.apigateway.dto.auth.AuthResponse;
import com.lynx.apigateway.dto.auth.LoginRequest;
import com.lynx.apigateway.dto.auth.MessageResponse;
import com.lynx.apigateway.dto.auth.RegisterRequest;
import com.lynx.apigateway.dto.auth.UserDto;
import com.lynx.apigateway.service.AuthFacade;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
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
        return authFacade.register(request);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return authFacade.login(request);
    }

    @PostMapping("/logout")
    public ResponseEntity<MessageResponse> logout(HttpServletRequest request) {
        return authFacade.logout(request.getHeader(HttpHeaders.COOKIE));
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(HttpServletRequest request) {
        return authFacade.me(request.getHeader(HttpHeaders.COOKIE));
    }
}