package com.jeonghyeon00.kotlin.carrot.module.dto.chatDto

import com.jeonghyeon00.kotlin.carrot.module.entity.ChatRoom
import java.time.LocalDateTime

data class ChatRoomsDto(
    val buyerId: String,
    val sellerId: String,
    val region: String,
    val message: String,
    val createdDate: LocalDateTime,
) {
    companion object {
        fun toDto(chatRoom: ChatRoom): ChatRoomsDto {
            with(chatRoom) {
                return ChatRoomsDto(
                    buyer.userId,
                    board.seller.userId,
                    board.region.regionName,
                    chatMessages[chatMessages.size - 1].content,
                    chatMessages[chatMessages.size - 1].getCreatedDateTime(),
                )
            }
        }
    }
}
