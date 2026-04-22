package com.lynx.apigateway.service.stub;

import com.lynx.apigateway.api.dto.request.LoginRequest;
import com.lynx.apigateway.api.dto.request.RegisterRequest;
import com.lynx.apigateway.api.dto.response.AuthResponse;
import com.lynx.apigateway.api.dto.response.UserDto;
import com.lynx.apigateway.error.UnauthorizedException;
import com.lynx.apigateway.service.AuthFacade;
import com.lynx.apigateway.security.JwtService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class StubAuthFacade implements AuthFacade {

    private static final UUID DEMO_USER_ID =
            UUID.fromString("550e8400-e29b-41d4-a716-446655440000");
    private static final String DEMO_EMAIL = "user01@gmail.com";
    private static final String DEMO_PASSWORD = "Parola123";
    private static final String DEMO_USERNAME = "h4ck3r";

    private final JwtService jwtService;

    public StubAuthFacade(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        UserDto user = new UserDto(
                DEMO_USER_ID,
                request.email(),
                request.firstName(),
                request.lastName(),
                request.username(),
                request.dateOfBirth(),
                LocalDateTime.now(),
                true
        );

        String token = jwtService.generateToken(user.id(), user.email(), user.username());

        return new AuthResponse(
                "User registered successfully",
                token,
                user
        );
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        if (!DEMO_EMAIL.equals(request.email()) || !DEMO_PASSWORD.equals(request.password())) {
            throw new UnauthorizedException("Invalid email or password.");
        }

        UserDto user = new UserDto(
                DEMO_USER_ID,
                DEMO_EMAIL,
                "Cristi",
                "Popescu",
                DEMO_USERNAME,
                java.time.LocalDate.of(2000, 10, 10),
                LocalDateTime.of(2026, 4, 15, 12, 12, 12),
                true
        );

        String token = jwtService.generateToken(user.id(), user.email(), user.username());

        return new AuthResponse(
                "User logged in successfully",
                token,
                user
        );
    }
}