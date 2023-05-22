package com.jeonghyeon00.kotlin.carrot.module.entity

import com.jeonghyeon00.kotlin.carrot.module.constants.BoardStatus
import com.jeonghyeon00.kotlin.carrot.module.constants.Category
import javax.persistence.*

@Entity
class Board(
    @Enumerated(EnumType.STRING)
    val category: Category,
    val title: String,
    val description: String,
    val viewCount: Long,
    val price: Int,

    @Enumerated(EnumType.STRING)
    val boardStatus: BoardStatus,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    val region: Region

) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val boardId: Long = 0
}
