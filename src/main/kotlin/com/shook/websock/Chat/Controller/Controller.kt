package com.shook.websock.Chat.Controller
import com.shook.websock.Chat.ChatRoom.ChatRoom
import com.shook.websock.Chat.DTO.BaseDTO
import com.shook.websock.Chat.DTO.RoomDTO
import com.shook.websock.Chat.Service.ChatService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/chat")
class Controller(private val service: ChatService) {

    @PostMapping()
    fun createRoom(@RequestBody roomDTO: RoomDTO): BaseDTO {
        service.createRoom(id = roomDTO.id)
        return BaseDTO(200, "${roomDTO.id} is created")
    }

    @DeleteMapping("/delete/{id}")
    fun terminateRoom(@PathVariable("id") id: String): BaseDTO {
        service.terminateRoom(id = id)
        return BaseDTO(200, "${id} is deleted")
    }
}