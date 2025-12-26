package com.example.springbootwebsocketangularv002.controller;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WsChatMessage {

    private String sender;
    private String content;
    private WsChatMessageType Type;
}
