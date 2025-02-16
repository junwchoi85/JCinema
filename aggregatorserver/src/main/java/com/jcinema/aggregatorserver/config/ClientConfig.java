package com.jcinema.aggregatorserver.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.jcinema.aggregatorserver.service.client.UsersSummaryClient;


@Configuration
public class ClientConfig {
    
    @Value("{app.baseUrl}")
    private String baseUrl;

    /**
     * Create a UsersSummaryClient instance
     * 
     * @return UsersSummaryClient instance
     * 
     */
    @Bean
    UsersSummaryClient usersClient() {
        // Create a WebClient instance
        WebClient webClient = WebClient.builder()
            .baseUrl(baseUrl)
            .build();
        // Create a WebClientAdapter instance
        WebClientAdapter adapter = WebClientAdapter.create(webClient);
        // Create an HttpServiceProxyFactory instance
        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
        // Create a UsersSummaryClient instance
        return factory.createClient(UsersSummaryClient.class);
    }
}
