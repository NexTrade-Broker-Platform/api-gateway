package com.lynx.apigateway.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class RateLimitingFilter extends OncePerRequestFilter {

    private final Map<String, Bucket> loginBuckets = new ConcurrentHashMap<>();
    private final Map<String, Bucket> orderBuckets = new ConcurrentHashMap<>();
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String path = request.getServletPath();
        String method = request.getMethod();

        if ("POST".equalsIgnoreCase(method) && "/api/users/login".equals(path)) {
            String ip = resolveClientIp(request);
            Bucket bucket = loginBuckets.computeIfAbsent(ip, k -> buildLoginBucket());
            if (!bucket.tryConsume(1)) {
                rejectWithRateLimit(response, "Too many login attempts. Please try again in 15 minutes.");
                return;
            }
        } else if ("POST".equalsIgnoreCase(method) && "/api/orders".equals(path)) {
            String userId = resolveUserId();
            if (userId != null) {
                Bucket bucket = orderBuckets.computeIfAbsent(userId, k -> buildOrderBucket());
                if (!bucket.tryConsume(1)) {
                    rejectWithRateLimit(response, "Order rate limit exceeded. Maximum 10 orders per minute.");
                    return;
                }
            }
        }

        chain.doFilter(request, response);
    }

    // 5 login attempts per IP per 15 minutes
    private Bucket buildLoginBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.builder()
                        .capacity(5)
                        .refillIntervally(5, Duration.ofMinutes(15))
                        .build())
                .build();
    }

    // 10 order submissions per authenticated user per minute
    private Bucket buildOrderBucket() {
        return Bucket.builder()
                .addLimit(Bandwidth.builder()
                        .capacity(10)
                        .refillIntervally(10, Duration.ofMinutes(1))
                        .build())
                .build();
    }

    private String resolveClientIp(HttpServletRequest request) {
        String forwarded = request.getHeader("X-Forwarded-For");
        if (forwarded != null && !forwarded.isBlank()) {
            return forwarded.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }

    private String resolveUserId() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated() && !"anonymousUser".equals(auth.getPrincipal())) {
            return auth.getName();
        }
        return null;
    }

    private void rejectWithRateLimit(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(objectMapper.writeValueAsString(
                Map.of("error", Map.of("code", "RATE_LIMIT_EXCEEDED", "message", message))
        ));
    }
}
