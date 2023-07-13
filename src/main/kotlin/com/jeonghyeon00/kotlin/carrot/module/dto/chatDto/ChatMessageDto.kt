package com.jeonghyeon00.kotlin.carrot.module.dto.chatDto

import com.jeonghyeon00.kotlin.carrot.module.entity.ChatMessage
import java.time.LocalDateTime

data class ChatMessageDto(
    val userName: String,
    val content: String,
    val createdDate: LocalDateTime,
) {
    companion object {
        fun chatMessageToDto(chatMessage: ChatMessage): ChatMessageDto {
            with(chatMessage) {
                return ChatMessageDto(
                    sender.userName,
                    content,
                    chatMessage.getCreatedDateTime(),
                )
            }
        }
    }
}
