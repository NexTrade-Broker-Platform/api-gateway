package com.lynx.apigateway.service;

import com.lynx.apigateway.api.dto.request.LoginRequest;
import com.lynx.apigateway.api.dto.request.RegisterRequest;
import com.lynx.apigateway.api.dto.response.AuthResponse;

public interface AuthFacade {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}