package com.jeonghyeon00.kotlin.carrot.module.dto.boardDto

import com.jeonghyeon00.kotlin.carrot.module.dto.imageDto.ImageRes
import com.jeonghyeon00.kotlin.carrot.module.entity.Board

data class BoardRes(
    val title: String,
    val userId: String,
    val nickname: String,
    val temperature: Float,
    val description: String,
    val viewCount: Long,
    val category: String,
    val regionName: String,
    val images: List<ImageRes>,
) {
    companion object {
        fun toBoardRes(board: Board): BoardRes {
            with(board) {
                return BoardRes(
                    title,
                    seller.userId,
                    seller.nickname,
                    seller.temperature,
                    description,
                    viewCount,
                    category.krName,
                    region.regionName,
                    images.map {
                        ImageRes.toDto(it)
                    },
                )
            }
        }
    }
}
