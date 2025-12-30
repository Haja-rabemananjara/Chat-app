package com.example.springbootwebsocketangularv002.controller;

import lombok.*;

/**
 * WebSocket Chat Message model representing a chat message.
 * It contains the sender's name, message content, and message type.
 * This class uses Lombok annotations to generate boilerplate code such as getters, setters,
 * constructors, and builder pattern methods.
 * Overall, it serves as a data transfer object for chat messages in the WebSocket communication.
 * @author Haja Rabemananjara
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WsChatMessage {

    private String content;
    private String sender;
    private WsChatMessageType type;
}
