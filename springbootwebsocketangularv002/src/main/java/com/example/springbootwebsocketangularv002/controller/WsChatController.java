package com.example.springbootwebsocketangularv002.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

/**
 * WebSocket Chat Controller to handle chat messages and user events.
 * It maps incoming messages to specific methods and sends responses to designated topics.
 * It uses Spring's messaging annotations to define message mappings and destinations.
 * The @Controller annotation indicates that this class serves as a web controller.
 * Overall, this class manages chat-related WebSocket interactions in the application.
 * @author Haja Rabemananjara
 */
@Controller
public class WsChatController {

    /**
     * Handle sending chat messages and broadcast them to all subscribers of /topic/public.
     * @param msg The chat message to be sent.
     * @return The chat message to be broadcasted.
     */
    @MessageMapping("chat.sendMessage") // maps to /app/chat.sendMessage
    @SendTo("/topic/public") // sends to all subscribers of /topic/public
    public WsChatMessage sendMessage(@Payload WsChatMessage msg) {
        // Log the received message (for demonstration purposes)
        System.out.println("Message received from " + msg.getSender() + ": " + msg.getContent());

        // Return the message to be sent to subscribers
        return msg;
    }

    /**
     * Handle new user addition and notify all subscribers of /topic/chat.
     * It adds the username to the WebSocket session attributes for future reference.
     * @param msg The chat message containing user information.
     * @param headerAccessor The SimpMessageHeaderAccessor to access session attributes.
     * @return The chat message to be sent to subscribers.
     */
    @MessageMapping("chat.addUser")
    @SendTo("/topic/chat")
    public WsChatMessage addUser(@Payload WsChatMessage msg, SimpMessageHeaderAccessor headerAccessor) {
        // Add username in web socket session
        headerAccessor.getSessionAttributes().put("username", msg.getSender());
        
        // Log the new user addition (for demonstration purposes)
        System.out.println("User joined: " + msg.getSender());

        // Return the message to be sent to subscribers
        return msg;
    }
}
