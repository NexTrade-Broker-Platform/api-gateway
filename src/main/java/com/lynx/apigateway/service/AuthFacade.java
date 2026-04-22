package com.lynx.apigateway.service;

import com.lynx.apigateway.dto.request.LoginRequest;
import com.lynx.apigateway.dto.request.RegisterRequest;
import com.lynx.apigateway.dto.response.AuthResponse;

public interface AuthFacade {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}