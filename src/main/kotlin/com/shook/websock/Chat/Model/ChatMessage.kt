package com.shook.websock.Chat.Model

public enum class MessageType {
    ENTER, CHAT
}
public data class ChatMessage(
    var type: MessageType,
    var content: String?,
    var sender: String,
    var roomId: String
)
