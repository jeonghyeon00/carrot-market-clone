package com.jeonghyeon00.kotlin.carrot.module.entity

import com.jeonghyeon00.kotlin.carrot.module.constants.BoardStatus
import com.jeonghyeon00.kotlin.carrot.module.constants.Category
import org.hibernate.annotations.Formula
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
    var boardStatus: BoardStatus,

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
    var buyer: User? = null

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "board", cascade = [CascadeType.ALL], orphanRemoval = true)
    var images: MutableList<Image> = mutableListOf()

    @Formula("(select count(*) from wish_list where wish_list.board_id = board_id)")
    val wishListCount: Int? = null
}
