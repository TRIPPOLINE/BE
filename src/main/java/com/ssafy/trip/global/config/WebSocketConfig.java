package com.ssafy.trip.global.config;

import com.ssafy.trip.recommend.service.TripRecommendationHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {
    @Value("${open.ai.key}")
    String apiKey;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(getRecommendationHandler(), "/trip-recommendation")
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler getRecommendationHandler() {
        return new TripRecommendationHandler(apiKey);
    }
}
