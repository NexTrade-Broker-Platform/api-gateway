package com.lynx.apigateway.service.impl;

import com.lynx.apigateway.dto.auth.AuthResponse;
import com.lynx.apigateway.dto.auth.AuthServiceResponse;
import com.lynx.apigateway.dto.auth.LoginRequest;
import com.lynx.apigateway.dto.auth.MessageResponse;
import com.lynx.apigateway.dto.auth.RegisterRequest;
import com.lynx.apigateway.dto.auth.UserDto;
import com.lynx.apigateway.service.AuthFacade;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.server.ResponseStatusException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;

@Service
public class AuthServiceFacade implements AuthFacade {

    private final RestClient restClient;
    private final String authServiceUrl;
    private final ObjectMapper objectMapper;

    public AuthServiceFacade(
            RestClient.Builder restClientBuilder,
            @Value("${services.auth.url}") String authServiceUrl,
            ObjectMapper objectMapper
    ) {
        this.restClient = restClientBuilder.build();
        this.authServiceUrl = authServiceUrl;
        this.objectMapper = objectMapper;
    }

    @Override
    public ResponseEntity<AuthResponse> register(RegisterRequest request) {
        return forwardAuthRequest("/users/register", request);
    }

    @Override
    public ResponseEntity<AuthResponse> login(LoginRequest request) {
        return forwardAuthRequest("/users/login", request);
    }

    @Override
    public ResponseEntity<MessageResponse> logout(String cookieHeader) {
        return restClient.post()
                .uri(authServiceUrl + "/users/logout")
                .header(HttpHeaders.COOKIE, safeCookie(cookieHeader))
                .exchange((clientRequest, clientResponse) -> {
                    HttpStatusCode statusCode = clientResponse.getStatusCode();
                    String responseBody = new String(clientResponse.getBody().readAllBytes());

                    if (!statusCode.is2xxSuccessful()) {
                        throw new ResponseStatusException(statusCode, responseBody);
                    }

                    HttpHeaders responseHeaders = new HttpHeaders();
                    copySetCookie(clientResponse.getHeaders(), responseHeaders);
                    responseHeaders.setContentType(MediaType.APPLICATION_JSON);

                    MessageResponse response = new MessageResponse("User logged out successfully");

                    return new ResponseEntity<>(
                            response,
                            responseHeaders,
                            statusCode
                    );
                });
    }

    @Override
    public ResponseEntity<UserDto> me(String cookieHeader) {
        return restClient.get()
                .uri(authServiceUrl + "/users/me")
                .header(HttpHeaders.COOKIE, safeCookie(cookieHeader))
                .exchange((clientRequest, clientResponse) -> {
                    HttpStatusCode statusCode = clientResponse.getStatusCode();
                    String responseBody = new String(clientResponse.getBody().readAllBytes());

                    if (!statusCode.is2xxSuccessful()) {
                        throw new ResponseStatusException(statusCode, responseBody);
                    }

                    UserDto user = objectMapper.readValue(responseBody, UserDto.class);

                    HttpHeaders responseHeaders = new HttpHeaders();
                    responseHeaders.setContentType(MediaType.APPLICATION_JSON);

                    return new ResponseEntity<>(
                            user,
                            responseHeaders,
                            statusCode
                    );
                });
    }

    private ResponseEntity<AuthResponse> forwardAuthRequest(String path, Object requestBody) {
        return restClient.post()
                .uri(authServiceUrl + path)
                .contentType(MediaType.APPLICATION_JSON)
                .body(requestBody)
                .exchange((clientRequest, clientResponse) -> {
                    HttpStatusCode statusCode = clientResponse.getStatusCode();

                    HttpHeaders responseHeaders = new HttpHeaders();
                    copySetCookie(clientResponse.getHeaders(), responseHeaders);
                    responseHeaders.setContentType(MediaType.APPLICATION_JSON);

                    String responseBody = new String(clientResponse.getBody().readAllBytes());

                    if (!statusCode.is2xxSuccessful()) {
                        throw new ResponseStatusException(statusCode, responseBody);
                    }

                    AuthServiceResponse authServiceResponse =
                            objectMapper.readValue(responseBody, AuthServiceResponse.class);

                    AuthResponse gatewayResponse = new AuthResponse(
                            authServiceResponse.message(),
                            null,
                            authServiceResponse.user()
                    );

                    return new ResponseEntity<>(
                            gatewayResponse,
                            responseHeaders,
                            statusCode
                    );
                });
    }

    private String safeCookie(String cookieHeader) {
        return cookieHeader == null ? "" : cookieHeader;
    }

    private void copySetCookie(HttpHeaders source, HttpHeaders target) {
        List<String> setCookies = source.get(HttpHeaders.SET_COOKIE);

        if (setCookies != null && !setCookies.isEmpty()) {
            target.put(HttpHeaders.SET_COOKIE, setCookies);
        }
    }
}
