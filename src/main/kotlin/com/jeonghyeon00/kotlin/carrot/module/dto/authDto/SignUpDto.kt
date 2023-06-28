package com.jeonghyeon00.kotlin.carrot.module.dto.authDto

data class SignUpDto(
    val userId: String,
    val userName: String,
    val password: String,
    val nickname: String,
    val phoneNumber: String,
)
