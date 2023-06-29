package com.jeonghyeon00.kotlin.carrot.module.dto.imageDto

import com.jeonghyeon00.kotlin.carrot.module.entity.Board
import com.jeonghyeon00.kotlin.carrot.module.entity.Image

data class ImageReq(
    val imageName: String,
    val imageUrl: String,
    val imageSize: Long,
) {
    companion object {
        fun ImageReq.toImage(board: Board): Image {
            return Image(
                imageName,
                imageUrl,
                imageSize,
                board,
            )
        }
    }
}
