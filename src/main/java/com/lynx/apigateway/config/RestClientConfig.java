package com.lynx.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;

@Configuration
public class RestClientConfig {

    @Bean
    public RestClient.Builder restClientBuilder(
            @Value("${internal.api-key}") String internalSecret
    ) {
        return RestClient.builder()
                .defaultHeader("X-INTERNAL-KEY", internalSecret);
    }
}