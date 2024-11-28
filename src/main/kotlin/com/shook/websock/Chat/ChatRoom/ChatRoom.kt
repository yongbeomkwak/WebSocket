package com.shook.websock.Chat.ChatRoom

import com.shook.websock.Broadcast.Service.BroadcastService
import com.shook.websock.Chat.Entity.ChatMessage
import com.shook.websock.Chat.Entity.MessageType
import org.springframework.web.socket.WebSocketSession
import java.util.stream.Stream

public class ChatRoom(val roomId: String) {
    private var sessions: MutableSet<WebSocketSession> = mutableSetOf()

    public fun handleActions(session: WebSocketSession, message: ChatMessage, service: BroadcastService) {
        if (message.type == MessageType.ENTER) {
            sessions.add(session)
            message.content = message.sender + "님이 입장했습니다."
            joinMessage(message, service, session)
        }  else {
            if (sessions.contains(session)) {
                sendMessage(message, service)
            }
        }
    }

    public fun leave(session: WebSocketSession) {
        sessions.remove(session)
    }

    public fun terminate() {
        sessions.clear()
    }

    public fun sessionStream(): Stream<WebSocketSession> {
        return sessions.parallelStream()
    }

    private fun<T> sendMessage(message: T, service: BroadcastService) {
        sessions.parallelStream().forEach {  service.sendMessage(it, message)}
    }

    private fun<T> joinMessage(message: T, service: BroadcastService, session: WebSocketSession) {
        service.sendMessage(session, message)
    }
}