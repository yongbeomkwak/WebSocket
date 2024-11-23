package com.shook.websock.Chat.Service

import com.fasterxml.jackson.databind.ObjectMapper
import com.shook.websock.Chat.ChatRoom.ChatRoom
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import java.util.UUID

@Service
public class ChatService(private var chatRooms: LinkedHashMap<String, ChatRoom>) {

    private  val objectMapper: ObjectMapper = ObjectMapper()

    public fun findAllRoom(): List<ChatRoom> {
        return chatRooms.values.toList()
    }

    public fun findByRoomId(roomId: String): ChatRoom? {
        return chatRooms.get(roomId)
    }

    public fun createRoom(id: String): ChatRoom {
        val chatRoom = ChatRoom(roomId = id)
        chatRooms[id] = chatRoom // 방 생성
        return chatRoom
    }

    public fun<T> sendMessage(session: WebSocketSession, message: T) {
        session.sendMessage(TextMessage(objectMapper.writeValueAsString(message)))
    }
}