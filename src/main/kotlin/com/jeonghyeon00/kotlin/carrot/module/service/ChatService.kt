package com.jeonghyeon00.kotlin.carrot.module.service

import com.jeonghyeon00.kotlin.carrot.module.dto.chatDto.ChatMessageDto
import com.jeonghyeon00.kotlin.carrot.module.dto.chatDto.ChatRoomsDto
import com.jeonghyeon00.kotlin.carrot.module.entity.ChatRoom
import com.jeonghyeon00.kotlin.carrot.module.global.exception.BaseException
import com.jeonghyeon00.kotlin.carrot.module.global.exception.BaseExceptionCode
import com.jeonghyeon00.kotlin.carrot.module.repository.*
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ChatService(
    private val chatRoomRepository: ChatRoomRepository,
    private val chatMessageRepository: ChatMessageRepository,
    private val userRepository: UserRepository,
    private val boardRepository: BoardRepository,
    private val imageRepository: ImageRepository,
) {
    fun makeChatRoom(boardId: Long, userId: String): Boolean {
        val user = userRepository.getReferenceById(userId)
        val board = boardRepository.getReferenceById(boardId)
        chatRoomRepository.save(ChatRoom(user, board))
        return true
    }

    @Transactional
    fun getAllMessages(chatroomId: Long, userId: String): List<ChatMessageDto> {
        val chatRoom = chatRoomRepository.getReferenceById(chatroomId)
        if (chatRoom.buyer.userId != userId && chatRoom.board.seller.userId != userId) {
            throw BaseException(BaseExceptionCode.NOT_YOUR_CHATROOM)
        }
        return chatMessageRepository.findAllByChatRoom(chatRoom).map {
            ChatMessageDto.chatMessageToDto(it)
        }
    }

    @Transactional
    fun getChatRooms(userId: String): List<ChatRoomsDto> {
        return chatRoomRepository.findAllByUserId(userId).map {
            ChatRoomsDto.toDto(it)
        }
    }
}
