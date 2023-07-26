package com.jeonghyeon00.kotlin.carrot.module.repository

import com.jeonghyeon00.kotlin.carrot.module.entity.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface ChatRoomRepository : JpaRepository<ChatRoom, Long> {
    @Query(
        value = " select distinct c from ChatRoom c join fetch c.buyer cb join fetch c.board cb2 join fetch cb2.seller cbseller join fetch c.chatMessages join fetch cb2.region where cb.userId =:userId or cbseller.userId =:userId",
    )
    fun findAllByUserId(@Param(value = "userId") userId: String): List<ChatRoom>
}
