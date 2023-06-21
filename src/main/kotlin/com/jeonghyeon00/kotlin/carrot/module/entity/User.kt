package com.jeonghyeon00.kotlin.carrot.module.entity

import com.jeonghyeon00.kotlin.carrot.module.constants.Authority
import javax.persistence.*

@Entity
@Table(name = "users")
class User(
    @Id
    val userId: String,
    val password: String,
    val userName: String,
    @Enumerated(EnumType.STRING)
    val authority: Authority,
    val nickname: String,
    val phoneNumber: String,
    val temperature: Float,
) : BaseTimeEntity() {
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_region")
    val regions: List<Region> = listOf()
}
