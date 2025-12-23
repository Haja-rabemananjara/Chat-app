package com.example.springbootwebsocketangularv002.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * WebSocket configuration class.
 * It enables WebSocket message handling, backed by a message broker.
 * It registers STOMP endpoints and configures message broker options.
 * It is annotated with @Configuration to indicate that it is a source of bean definitions
 * and with @EnableWebSocketMessageBroker to enable WebSocket message handling.
 * Overall, this class sets up the necessary configuration for WebSocket communication in the application.
 * @author Haja Rabemananjara
 */
@Configuration
@EnableWebSocketMessageBroker
public class Config implements WebSocketMessageBrokerConfigurer {

    // Allow webSocket connection. This allows the angular client to connect.
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") //  PATH A REVOIR!!!!!
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();
    }

    // Configure message broker options.
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }


}
