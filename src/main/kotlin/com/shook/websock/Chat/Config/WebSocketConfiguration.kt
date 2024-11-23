package com.shook.websock.Chat.Config

import com.fasterxml.jackson.databind.ObjectMapper
import com.shook.websock.Chat.ChatRoom.ChatRoom
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import com.shook.websock.Chat.Handler.WebSocketHandler
import com.shook.websock.Chat.Service.ChatService
import java.util.LinkedHashMap

@Configuration
@EnableWebSocket
class WebSocketConfiguration(private val service: ChatService): WebSocketConfigurer {

    private val webSocketHandler: WebSocketHandler = WebSocketHandler(service = service )

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(webSocketHandler,"/ws/chat")
            .setAllowedOrigins("*")
    }
}