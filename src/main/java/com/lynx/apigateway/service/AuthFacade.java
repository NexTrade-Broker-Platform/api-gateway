package com.lynx.apigateway.service;

import com.lynx.apigateway.dto.auth.AuthResponse;
import com.lynx.apigateway.dto.auth.LoginRequest;
import com.lynx.apigateway.dto.auth.MessageResponse;
import com.lynx.apigateway.dto.auth.RegisterRequest;
import com.lynx.apigateway.dto.auth.UserDto;
import org.springframework.http.ResponseEntity;

public interface AuthFacade {
    ResponseEntity<AuthResponse> register(RegisterRequest request);
    ResponseEntity<AuthResponse> login(LoginRequest request);
    ResponseEntity<MessageResponse> logout(String cookieHeader);
    ResponseEntity<UserDto> me(String cookieHeader);
}