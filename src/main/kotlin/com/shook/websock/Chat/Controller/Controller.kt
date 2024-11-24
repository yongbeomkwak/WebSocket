package com.shook.websock.Chat.Controller
import com.shook.websock.Chat.ChatRoom.ChatRoom
import com.shook.websock.Chat.DTO.RoomDTO
import com.shook.websock.Chat.Service.ChatService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class Controller(private val service: ChatService) {

    @PostMapping()
    fun createRoom(@RequestBody roomDTO: RoomDTO): String {
        service.createRoom(id = roomDTO.id)
        println("${roomDTO.id} is created")
        return "${roomDTO.id} is created"
    }

    @DeleteMapping("/delete/{id}")
    fun terminateRoom(@PathVariable("id") id: String): String {
        service.terminateRoom(id = id)
        return "${id} is terminated"
    }
}