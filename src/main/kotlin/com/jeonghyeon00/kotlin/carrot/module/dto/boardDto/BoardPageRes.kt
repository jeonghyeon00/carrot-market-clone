package com.jeonghyeon00.kotlin.carrot.module.dto.boardDto

import com.jeonghyeon00.kotlin.carrot.module.constants.BoardStatus
import com.jeonghyeon00.kotlin.carrot.module.entity.Board

data class BoardPageRes(
    val title: String,
    val boardStatus: BoardStatus,
    val regionName: String,
    val wishListCount: Int,
) {
    companion object {
        fun toBoardPageRes(board: Board): BoardPageRes {
            with(board) {
                return BoardPageRes(
                    title,
                    boardStatus,
                    region.regionName,
                    wishListCount ?: 0,
                )
            }
        }
    }
}
