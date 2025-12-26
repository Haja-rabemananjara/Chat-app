package com.example.springbootwebsocketangularv002.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.core.MessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

/**
 * WebSocket Event Listener to handle WebSocket events such as connection and disconnection.
 * It listens for specific events and performs actions accordingly.
 * For example, when a user disconnects, it logs the event and notifies other users.
 * It uses Spring's event listener mechanism to react to WebSocket events.
 * This class is annotated with @Component to be detected during component scanning
 * and with @RequiredArgsConstructor to automatically generate a constructor for final fields.
 * It also uses Lombok's @Slf4j to provide a logger for logging messages.
 * The MessageSendingOperations is used to send messages to specific destinations.
 * Overall, this class enhances the WebSocket functionality by providing event-driven responses.
 * @author Haja Rabemananjara
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class WsEventListener {

    // method called when user close oage in browser.
    @EventListener
    public void handleWsDisconnectListener(SessionDisconnectEvent event){

        // To Listen to another even, create the another method with NewEvent as argument
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        String username = (String) headerAccessor.getSessionAttributes().get("username");
        if(username !=null){
            log.info("User disconnected: {} ", username);
            var message = WsChatMessage.builder()
                    .type(WsChatMessageType.LEAVE)
                    .build();
            // This is to send message to all subscribers of the topic
            messageSendingOperations.convertAndSend("/topic/public",message);
        }
    }


}
