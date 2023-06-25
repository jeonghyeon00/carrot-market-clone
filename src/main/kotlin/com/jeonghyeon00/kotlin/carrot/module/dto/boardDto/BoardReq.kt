package com.jeonghyeon00.kotlin.carrot.module.dto.boardDto

import com.jeonghyeon00.kotlin.carrot.module.constants.BoardStatus
import com.jeonghyeon00.kotlin.carrot.module.constants.Category
import com.jeonghyeon00.kotlin.carrot.module.entity.Board
import com.jeonghyeon00.kotlin.carrot.module.entity.Region
import com.jeonghyeon00.kotlin.carrot.module.entity.User

data class BoardReq(
    val category: Category,
    val title: String,
    val description: String,
    val price: Int,
) {
    companion object {
        fun BoardReq.toBoard(user: User, region: Region): Board {
            return Board(
                category,
                title,
                description,
                0,
                price,
                BoardStatus.PENDING,
                region,
            ).also {
                it.seller = user
            }
        }
    }
}
