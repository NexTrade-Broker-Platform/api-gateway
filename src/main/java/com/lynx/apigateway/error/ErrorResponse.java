package com.lynx.apigateway.error;

import java.util.Map;

public record ErrorResponse(
        String code,
        String message,
        Map<String, String> details
) {
}