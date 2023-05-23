package com.jeonghyeon00.kotlin.carrot.module.service

import com.jeonghyeon00.kotlin.carrot.module.entity.RefreshToken
import com.jeonghyeon00.kotlin.carrot.module.repository.RefreshTokenRepository
import org.springframework.stereotype.Service

@Service
class RedisService(
    private val refreshTokenRepository: RefreshTokenRepository,
) {
    fun setValues(userId: String, refreshToken: String) {
        refreshTokenRepository.save(
            RefreshToken(
                userId,
                refreshToken,
            ),
        )
    }

    fun getValues(userId: String): RefreshToken? {
        return refreshTokenRepository.findByUserId(userId)
    }
}
