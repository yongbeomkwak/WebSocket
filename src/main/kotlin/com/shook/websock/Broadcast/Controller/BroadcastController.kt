package com.shook.websock.Broadcast.Controller

import com.shook.websock.Base.DTO.BaseDTO
import com.shook.websock.Broadcast.DTO.BroadcastDTO
import com.shook.websock.Broadcast.Entity.Broadcast
import com.shook.websock.Broadcast.Service.BroadcastService
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.*

@Tag(name = "방송", description = "방송을 생성, 삭제, 조회")
@RestController
@RequestMapping("/broadcast")
class BroadcastController(private val service: BroadcastService ) {

    @PostMapping()
    fun createBroadcast(@RequestBody broadcastDTO: BroadcastDTO): BaseDTO {
        val model = broadcastDTO.toModel()
        return service.createBroadcast(new = model)
    }

    @DeleteMapping("/delete/{id}")
    fun deleteBroadcast(@PathVariable("id") id: String): BaseDTO {
        return service.deleteBroadcast(id = id)
    }

    @GetMapping("/all")
    fun getAllBroadcast(): List<Broadcast> {
        return service.getAllBroadcast()
    }
}