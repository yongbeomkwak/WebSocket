package com.shook.websock.Chat.Model

import com.fasterxml.jackson.annotation.JsonAutoDetect

public enum class MessageType {
        ENTER, CHAT, LEAVE
}


public data class ChatMessage(
    public var type: MessageType,
    public var content: String?,
    public var sender: String,
    public var roomId: String
) {
    /* com.fasterxml.jackson.databind.exc.InvalidDefinitionException:
    Cannot construct instance of `com.shook.websock.Chat.Model.ChatMessage`
    (no Creators, like default constructor, exist): cannot deserialize from Object value
    버그로 인한 기본 생성자.
 */
    constructor() : this(MessageType.CHAT, null, "", "")
}
