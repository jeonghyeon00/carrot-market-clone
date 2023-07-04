package com.jeonghyeon00.kotlin.carrot.module.dto.imageDto

import com.jeonghyeon00.kotlin.carrot.module.entity.Image

data class ImageRes(
    val imageName: String,
    val imageUrl: String,
    val imageSize: Long,
) {
    companion object {
        fun toDto(image: Image): ImageRes {
            with(image) {
                return ImageRes(
                    imageName,
                    imageUrl,
                    imageSize,
                )
            }
        }
    }
}
