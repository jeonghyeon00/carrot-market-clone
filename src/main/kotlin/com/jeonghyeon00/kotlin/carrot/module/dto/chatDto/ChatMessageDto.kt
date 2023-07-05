package com.jeonghyeon00.kotlin.carrot.module.dto.chatDto

import com.jeonghyeon00.kotlin.carrot.module.entity.ChatMessage

data class ChatMessageDto(
    val userName: String,
    val content: String,
    val chatroomId: Long,
) {
    companion object {
        fun chatMessageToDto(chatMessage: ChatMessage): ChatMessageDto {
            with(chatMessage) {
                return ChatMessageDto(
                    sender.userName,
                    content,
                    chatRoom.chatRoomId,
                )
            }
        }
    }
}
