package com.jeonghyeon00.kotlin.carrot.module.controller

import com.jeonghyeon00.kotlin.carrot.module.global.security.GetIdFromToken
import com.jeonghyeon00.kotlin.carrot.module.service.ChatService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ChatController(
    private val chatService: ChatService,
) {
    @PostMapping("/board/{boardId}/chat")
    fun makeChatRoom(@PathVariable(name = "boardId") boardId: Long, @GetIdFromToken userId: String): Boolean {
        return chatService.makeChatRoom(boardId, userId)
    }
}
