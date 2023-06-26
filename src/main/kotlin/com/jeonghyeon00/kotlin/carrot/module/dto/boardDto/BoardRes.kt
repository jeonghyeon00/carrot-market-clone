package com.jeonghyeon00.kotlin.carrot.module.dto.boardDto

import com.jeonghyeon00.kotlin.carrot.module.constants.BoardStatus
import com.jeonghyeon00.kotlin.carrot.module.entity.Board

data class BoardRes(
    val title: String,
    val boardStatus: BoardStatus,
    val regionName: String,
) {
    companion object {
        fun toBoardRes(board: Board): BoardRes {
            with(board) {
                return BoardRes(
                    title,
                    boardStatus,
                    region.regionName,
                )
            }
        }
    }
}
