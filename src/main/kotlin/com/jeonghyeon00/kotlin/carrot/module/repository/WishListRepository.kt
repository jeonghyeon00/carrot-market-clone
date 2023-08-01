package com.jeonghyeon00.kotlin.carrot.module.repository

import com.jeonghyeon00.kotlin.carrot.module.entity.Board
import com.jeonghyeon00.kotlin.carrot.module.entity.User
import com.jeonghyeon00.kotlin.carrot.module.entity.WishList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface WishListRepository : JpaRepository<WishList, Long> {
    fun existsByBoardAndUser(board: Board, user: User): Boolean
    fun deleteByBoardAndUser(board: Board, user: User): Int

    @Query(
        value = "select distinct wl from WishList wl join fetch wl.board wb join fetch wb.region where wl.user = :user",
    )
    fun getAllByUser(@Param(value = "user") user: User): List<WishList>
}
