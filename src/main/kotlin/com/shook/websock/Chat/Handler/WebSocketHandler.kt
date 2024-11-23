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
        println("Paylod: ${payload}")

        try {
            val chatMessage = objectMapper.readValue<ChatMessage>(payload)
            println("chatMessage: ${chatMessage}")
            val room = service.findByRoomId(chatMessage.roomId)
            println("room: ${room}")
            room?.handleActions(session, chatMessage, service)
        } catch(e: Exception) {
            println(e)
        }

    }
}
