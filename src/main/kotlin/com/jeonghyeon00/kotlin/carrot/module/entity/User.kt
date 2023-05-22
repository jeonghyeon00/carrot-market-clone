package com.jeonghyeon00.kotlin.carrot.module.entity

import com.jeonghyeon00.kotlin.carrot.module.constants.Authority
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
class User(
    @Id
    val userId: String,
    val password: String,
    val userName: String,
    @Enumerated(EnumType.STRING)
    val authority: Authority
) : BaseTimeEntity()
