package com.jeonghyeon00.kotlin.carrot.module.dto.chatDto

data class MessageDto(
    val message: String,
    val token: String,
    val chatRoomId: Long,
)
