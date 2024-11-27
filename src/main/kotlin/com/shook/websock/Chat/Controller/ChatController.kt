package com.shook.websock.Chat.Controller
import com.shook.websock.Base.DTO.BaseDTO
import com.shook.websock.Chat.DTO.RoomDTO
import com.shook.websock.Chat.Service.ChatService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "채팅방", description = "채팅방 생성 및 삭제와 관련된 api")
@RestController
@RequestMapping("/chat")
class ChatController(private val service: ChatService) {

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