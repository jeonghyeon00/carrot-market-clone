package com.jeonghyeon00.kotlin.carrot.module.dto.chatDto

data class MessageDto(
    val message: String,
    val sender: String,
    val chatroomId: Long,
) {
    fun toDto(): ChatMessageDto {
        return ChatMessageDto(
            sender,
            message,
            chatroomId,
        )
    }
}
