package com.jeonghyeon00.kotlin.carrot.module.repository

import com.jeonghyeon00.kotlin.carrot.module.entity.Board
import com.jeonghyeon00.kotlin.carrot.module.entity.User
import com.jeonghyeon00.kotlin.carrot.module.entity.WishList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface WishListRepository : JpaRepository<WishList, Long> {
    fun existsByBoardAndUser(board: Board, user: User): Boolean
    fun deleteByBoardAndUser(board: Board, user: User): Int
}
