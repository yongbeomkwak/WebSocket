package com.shook.websock.Broadcast.Service

import com.fasterxml.jackson.databind.ObjectMapper
import com.shook.websock.Base.DTO.BaseDTO
import com.shook.websock.Broadcast.Entity.Broadcast
import com.shook.websock.Chat.ChatRoom.ChatRoom
import com.shook.websock.Chat.Entity.ChatMessage
import com.shook.websock.Chat.Entity.MessageType
import org.springframework.stereotype.Service
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession

@Service
class BroadcastService(private var broadcasts: HashMap<String, Broadcast>,
                       private var chatRooms: LinkedHashMap<String, ChatRoom>,
                       private  val objectMapper: ObjectMapper
    ) {

    fun createBroadcast(new: Broadcast): BaseDTO {
        if (broadcasts[new.id] == null) {
            broadcasts[new.id] = new
            createRoom(id = new.id)
            return BaseDTO(200, message = "${new.id} 방송을 시작합니다" )
        }

        return BaseDTO(200, message = "이미 ${new.id} 방송이 존재합니다.")
    }

    fun deleteBroadcast(id: String): BaseDTO {
        if (broadcasts[id] != null) {
            broadcasts.remove(id)
            terminateRoom(id = id)
            return BaseDTO(200, message = "성공적으로 ${id} 방송이 종료됐습니다.")
        }
        return BaseDTO(200, message = "${id} 방송은 이미 종료되어었습니다.")
    }

    fun getAllBroadcast(): List<Broadcast> {
        return broadcasts.values.toList()
    }

    fun findByRoomId(roomId: String): ChatRoom? {
        return chatRooms.get(roomId)
    }

    fun<T> sendMessage(session: WebSocketSession, message: T) {
        session.sendMessage(TextMessage(objectMapper.writeValueAsString(message)))
    }

    private fun createRoom(id: String): ChatRoom {
        val chatRoom = ChatRoom(roomId = id)

        if (findByRoomId(id) == null) {
            chatRooms[id] = chatRoom // 방 생성
        }

        return chatRoom
    }

    private fun terminateRoom(id: String) {
        val room = findByRoomId(id)
        val terminateMessage = ChatMessage(MessageType.TERMINATE, "채팅창이 종료되었습니다.", "시스템", "")
        if ( room != null) {
            room.sessionStream().forEach {
                sendMessage(it, terminateMessage)
            }
            room.terminate() // 세션 종료
            chatRooms.remove(id) // 방 삭제
        }
    }
}