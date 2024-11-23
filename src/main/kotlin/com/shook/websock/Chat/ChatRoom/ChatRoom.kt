package com.shook.websock.Chat.ChatRoom

import com.shook.websock.Chat.Model.ChatMessage
import com.shook.websock.Chat.Model.MessageType
import com.shook.websock.Chat.Service.ChatService
import org.springframework.web.socket.WebSocketSession

public class ChatRoom(val roomId: String) {
    private var sessions: MutableSet<WebSocketSession> = mutableSetOf()

    public fun handleActions(session: WebSocketSession, message: ChatMessage, service: ChatService) {
        if (message.type == MessageType.ENTER) {
            sessions.add(session)
            message.content = message.sender + "님이 입장했습니다."
        }
        sendMessage(message, service)
    }

    public fun<T> sendMessage(message: T, service: ChatService) {
        sessions.parallelStream().forEach { service.sendMessage(it, message) }
    }
}