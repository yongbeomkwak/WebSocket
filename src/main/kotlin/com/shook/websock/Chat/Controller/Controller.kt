package com.shook.websock.Chat.Controller
import com.shook.websock.Chat.ChatRoom.ChatRoom
import com.shook.websock.Chat.DTO.RoomDTO
import com.shook.websock.Chat.Service.ChatService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*

@RestController
@RequestMapping("/chat")
class Controller(private val service: ChatService) {

    @PostMapping()
    fun createRoom(@RequestBody roomDTO: RoomDTO): ChatRoom {
        return service.createRoom(roomDTO.id)
    }
}