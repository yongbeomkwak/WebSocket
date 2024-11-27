package com.shook.websock.Chat.Handler

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import com.shook.websock.Chat.Entity.ChatMessage
import com.shook.websock.Chat.Service.ChatService
import org.springframework.stereotype.Component
import org.springframework.web.socket.BinaryMessage
import org.springframework.web.socket.CloseStatus
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler

@Component
public class WebSocketHandler(private  val service: ChatService) :  TextWebSocketHandler() {

    private val objectMapper: ObjectMapper = ObjectMapper()
    private var roomIdMapper: HashMap<WebSocketSession, String> = HashMap<WebSocketSession, String>()

    override fun afterConnectionEstablished(session: WebSocketSession) {
        super.afterConnectionEstablished(session)
        println("접속 완료 ${session}")
    }

    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val payload = message.payload
        println("Paylod: ${payload}")

        try {
            val chatMessage = objectMapper.readValue<ChatMessage>(payload)
            println("chatMessage: ${chatMessage}")
            val room = service.findByRoomId(chatMessage.roomId)
            roomIdMapper[session] = chatMessage.roomId
            room?.handleActions(session, chatMessage, service)
        } catch(e: Exception) {
            println(e)
        }

    }

    override fun handleBinaryMessage(session: WebSocketSession, message: BinaryMessage) {
        val byteArray = message.payload.array()
        try {
            val chatMessage =  objectMapper.readValue<ChatMessage>(byteArray)
            println("chatMessage: ${chatMessage}")
            val room = service.findByRoomId(chatMessage.roomId)
            println("room: ${room}")
            roomIdMapper[session] = chatMessage.roomId
            room?.handleActions(session, chatMessage, service)
        } catch(e: Exception) {
            println(e)
        }

    }

    override fun afterConnectionClosed(session: WebSocketSession, status: CloseStatus) {
        super.afterConnectionClosed(session, status)
        val roomId = roomIdMapper[session]
        if (roomId != null) {
            val room = service.findByRoomId(roomId)
            room?.leave(session)
            println("session: ${session} 이 방 ${roomId}를 떠났습니다")
        }
    }
}
