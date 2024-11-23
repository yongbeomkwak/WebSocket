package com.shook.websock.handler

import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
public class WebSocketHandler :  TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        val textMessage = TextMessage("Welcome chatting server")
        session.sendMessage(textMessage)
    }
}
