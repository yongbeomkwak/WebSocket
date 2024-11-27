package com.shook.websock.Broadcast.DTO

import com.shook.websock.Broadcast.Entity.Broadcast

data class BroadcastDTO(
    public val id: String,
    public val title: String,
    public val owner: String,
    public val description: String) {

    fun toModel(): Broadcast {
        return Broadcast(id = this.id, title = this.title, owner = this.owner, description = this.description)
    }
}
