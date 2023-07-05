package com.jeonghyeon00.kotlin.carrot.module.entity

import javax.persistence.*

@Entity
class ChatRoom(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id")
    val buyer: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    val board: Board,
) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val chatRoomId: Long = 0
}
