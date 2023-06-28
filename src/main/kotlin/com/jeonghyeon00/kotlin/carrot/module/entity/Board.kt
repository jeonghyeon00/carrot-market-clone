package com.jeonghyeon00.kotlin.carrot.module.entity

import com.jeonghyeon00.kotlin.carrot.module.constants.BoardStatus
import com.jeonghyeon00.kotlin.carrot.module.constants.Category
import javax.persistence.*

@Entity
class Board(
    @Enumerated(EnumType.STRING)
    var category: Category,
    var title: String,
    var description: String,
    var viewCount: Long,
    var price: Int,

    @Enumerated(EnumType.STRING)
    val boardStatus: BoardStatus,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    val region: Region,

) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val boardId: Long = 0

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id")
    lateinit var seller: User

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    val buyer: User? = null
}
