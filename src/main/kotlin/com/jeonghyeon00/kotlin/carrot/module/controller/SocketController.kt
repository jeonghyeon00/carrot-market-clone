package com.jeonghyeon00.kotlin.carrot.module.controller

import com.jeonghyeon00.kotlin.carrot.module.dto.chatDto.MessageDto
import com.jeonghyeon00.kotlin.carrot.module.entity.ChatMessage
import com.jeonghyeon00.kotlin.carrot.module.global.security.JwtTokenProvider
import com.jeonghyeon00.kotlin.carrot.module.repository.ChatMessageRepository
import com.jeonghyeon00.kotlin.carrot.module.repository.ChatRoomRepository
import com.jeonghyeon00.kotlin.carrot.module.repository.UserRepository
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Controller
import javax.transaction.Transactional

@Controller
class SocketController(
    private val simpMessagingTemplate: SimpMessagingTemplate,
    private val chatMessageRepository: ChatMessageRepository,
    private val userRepository: UserRepository,
    private val chatRoomRepository: ChatRoomRepository,
    private val jwtTokenProvider: JwtTokenProvider,
) {
    @MessageMapping("/send")
    @Transactional
    fun send(message: MessageDto) {
        val userId = jwtTokenProvider.getUserPk(message.token.substring(6))
        chatMessageRepository.save(
            ChatMessage(
                message.message,
                userRepository.getReferenceById(userId),
                chatRoomRepository.getReferenceById(message.chatRoomId),
            ),
        )
        simpMessagingTemplate.convertAndSend("/topic/${message.chatRoomId}", message)
    }
}
