package com.jeonghyeon00.kotlin.carrot.module.entity

import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash

@RedisHash(value = "refreshToken", timeToLive = 1000L * 60 * 60 * 3)
class RefreshToken(
    @Id
    val userId: String,
    val refreshToken: String,
)
