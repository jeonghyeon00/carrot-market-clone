package com.jeonghyeon00.kotlin.carrot.module.entity

import javax.persistence.*

@Entity
class Image(
    val imageName: String,
    val imageUrl: String,
    val imageSize: Long,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    val board: Board
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val imageId: Long = 0
}
