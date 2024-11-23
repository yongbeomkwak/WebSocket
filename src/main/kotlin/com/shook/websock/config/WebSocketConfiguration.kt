package com.shook.websock.config

import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry
import com.shook.websock.handler.WebsocketHandler

@Configuration
@EnableWebSocket
class WebSocketConfiguration: WebSocketConfigurer {
    private val webSocketHandler: WebsocketHandler = WebsocketHandler()

    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.addHandler(webSocketHandler,"/ws/chat")
            .setAllowedOrigins("*")
    }
}