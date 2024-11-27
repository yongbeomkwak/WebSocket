package com.shook.websock.Broadcast.Service

import com.shook.websock.Base.DTO.BaseDTO
import com.shook.websock.Broadcast.Entity.Broadcast
import org.springframework.stereotype.Service

@Service
class BroadcastService {
    private var broadcasts: HashMap<String, Broadcast> = HashMap<String, Broadcast>()

    fun createBroadcast(new: Broadcast): BaseDTO {
        if (broadcasts[new.id] == null) {
            broadcasts[new.id] = new
            return BaseDTO(200, message = "${new.id} 방송을 시작합니다" )
        }
        return return BaseDTO(200, message = "이미 ${new.id} 방송이 존재합니다.")
    }

    fun deleteBroadcast(id: String): BaseDTO {
        if (broadcasts[id] != null) {
            broadcasts.remove(id)
            return BaseDTO(200, message = "성공적으로 ${id} 방송이 종료됐습니다.")
        }
        return BaseDTO(200, message = "${id} 방송은 이미 종료되어었습니다.")
    }

    fun getAllBroadcast(): List<Broadcast> {
        return broadcasts.values.toList()
    }
}