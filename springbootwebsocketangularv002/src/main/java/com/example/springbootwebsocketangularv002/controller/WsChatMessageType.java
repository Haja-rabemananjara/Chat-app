package com.example.springbootwebsocketangularv002.controller;

/**
 * Enum representing the types of WebSocket chat messages.
 * It defines three types of messages: JOIN, LEAVE, and CHAT.
 * This enum is used to categorize the messages exchanged in the chat application.
 * Overall, it helps in managing different chat events effectively.
 * @author Haja Rabemananjara
 */
public enum WsChatMessageType {

    JOIN,
    LEAVE,
    CHAT
}
