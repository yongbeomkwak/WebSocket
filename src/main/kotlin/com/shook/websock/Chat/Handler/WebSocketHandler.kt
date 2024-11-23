package com.shook.websock.Chat.Handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.shook.websock.Chat.Model.ChatMessage
import com.shook.websock.Chat.Service.ChatService
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
public class WebSocketHandler(private  val service: ChatService) :  TextWebSocketHandler() {

    private  val objectMapper: ObjectMapper = ObjectMapper()

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        val chatMessage = objectMapper.readValue<ChatMessage>(payload)
        val room = service.findByRoomId(chatMessage.roomId)
        room?.handleActions(session, chatMessage, service)
    }
}
