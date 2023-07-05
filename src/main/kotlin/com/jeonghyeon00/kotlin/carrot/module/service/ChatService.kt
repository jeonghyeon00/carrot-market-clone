package com.jeonghyeon00.kotlin.carrot.module.service

import com.jeonghyeon00.kotlin.carrot.module.entity.ChatRoom
import com.jeonghyeon00.kotlin.carrot.module.repository.BoardRepository
import com.jeonghyeon00.kotlin.carrot.module.repository.ChatMessageRepository
import com.jeonghyeon00.kotlin.carrot.module.repository.ChatRoomRepository
import com.jeonghyeon00.kotlin.carrot.module.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatRoomRepository: ChatRoomRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val userRepository: UserRepository,
    private val boardRepository: BoardRepository,
) {
    fun makeChatRoom(boardId: Long, userId: String): Boolean {
        val user = userRepository.getReferenceById(userId)
        val board = boardRepository.getReferenceById(boardId)
        chatRoomRepository.save(ChatRoom(user, board))
        return true
    }
}
