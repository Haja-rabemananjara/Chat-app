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

    /**
     * Register STOMP endpoints for WebSocket connections.
     * It defines the endpoint "/ws" and allows cross-origin requests from "http://localhost:4200".
     * It also enables SockJS fallback options for browsers that do not support WebSocket.
     * @param registry The StompEndpointRegistry to register endpoints.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws") //  PATH A REVOIR!!!!!
                .setAllowedOrigins("http://localhost:4200")
                .withSockJS();
    }

    /**
     * Configure message broker options.
     * It sets the application destination prefix to "/app" and enables a simple in-memory message broker
     * with the destination prefix "/topic".
     * @param registry The MessageBrokerRegistry to configure message broker options.
     */
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }


}
