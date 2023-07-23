package com.jeonghyeon00.kotlin.carrot.module.repository

import com.jeonghyeon00.kotlin.carrot.module.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ChatRoomRepository : JpaRepository<ChatRoom, Long> {
    @Query(
        value = " select c from ChatRoom c join c.buyer cb join c.board cb2 where cb.userId =:userId or cb2.seller.userId =:userId",
    )
    fun findAllByUserId(@Param(value = "userId") userId: String): List<ChatRoom>
}
