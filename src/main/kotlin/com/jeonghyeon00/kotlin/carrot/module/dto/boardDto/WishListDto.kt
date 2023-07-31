package com.jeonghyeon00.kotlin.carrot.module.dto.boardDto

import com.jeonghyeon00.kotlin.carrot.module.entity.WishList

data class WishListDto(
    val title: String,
    val price: Int,
    val regionName: String,
    val wishListCount: Int?,
) {
    companion object {
        fun toDto(wishList: WishList): WishListDto {
            with(wishList.board) {
                return WishListDto(
                    title,
                    price,
                    region.regionName,
                    wishListCount,
                )
            }
        }
    }
}
