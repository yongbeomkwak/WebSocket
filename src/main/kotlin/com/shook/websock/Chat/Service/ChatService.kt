package com.shook.websock.Chat.Service

import com.fasterxml.jackson.databind.ObjectMapper
import com.shook.websock.Chat.ChatRoom.ChatRoom
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

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

        if (findByRoomId(id) == null) {
            chatRooms[id] = chatRoom // 방 생성
        }

        return chatRoom
    }

    public fun terminateRoom(id: String) {
        val room = findByRoomId(id)

        if ( room != null) {
            room.terminate() // 세션 종료
            chatRooms.remove(id) // 방 삭제
        }

    }

    public fun<T> sendMessage(session: WebSocketSession, message: T) {
        session.sendMessage(TextMessage(objectMapper.writeValueAsString(message)))
    }
}