package com.jeonghyeon00.kotlin.carrot.module.service

import com.jeonghyeon00.kotlin.carrot.module.dto.authDto.SignUpDto
import com.jeonghyeon00.kotlin.carrot.module.constants.Authority
import com.jeonghyeon00.kotlin.carrot.module.dto.authDto.SignInDto
import com.jeonghyeon00.kotlin.carrot.module.dto.authDto.TokenDto
import com.jeonghyeon00.kotlin.carrot.module.entity.User
import com.jeonghyeon00.kotlin.carrot.module.global.exception.BaseException
import com.jeonghyeon00.kotlin.carrot.module.global.exception.BaseExceptionCode
import com.jeonghyeon00.kotlin.carrot.module.repository.UserRepository
import com.jeonghyeon00.kotlin.carrot.module.global.security.JwtTokenProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class AuthService(
    private val userRepository: UserRepository,
    private val tokenProvider: JwtTokenProvider,
    private val authenticationManagerBuilder: AuthenticationManagerBuilder,
    private val redisService: RedisService,
) {
    @Transactional
    fun signUp(signUpDto: SignUpDto): Boolean {
        with(signUpDto) {
            if (userRepository.existsByUserId(userId)) throw BaseException(BaseExceptionCode.USER_ID_CONFLICT)
            userRepository.save(
                User(
                    userId,
                    BCryptPasswordEncoder().encode(password),
                    userName,
                    Authority.USER,
                    nickname,
                    phoneNumber,
                    36.5F,
                ),
            )
        }
        return true
    }

    fun signIn(signInDto: SignInDto): TokenDto {
        signInDto.apply {
            val credential = UsernamePasswordAuthenticationToken(userId, password)
            val authentication = authenticationManagerBuilder.`object`.authenticate(credential)
            val token = tokenProvider.createToken(authentication)
            redisService.setValues(userId, token.refreshToken)
            return token
        }
    }

    fun refresh(tokenDto: TokenDto): TokenDto {
        val userId = tokenProvider.getUserPk(tokenDto.accessToken)
        val savedRefreshToken = redisService.getValues(userId)
        if (tokenDto.refreshToken == savedRefreshToken?.refreshToken) {
            val authentication = tokenProvider.getAuthentication(tokenDto.accessToken)
            val token = tokenProvider.createToken(authentication)
            return TokenDto(token.accessToken, token.refreshToken).also {
                redisService.setValues(userId, token.refreshToken)
            }
        } else {
            throw BaseException(BaseExceptionCode.REFRESH_TOKEN_MISMATCH)
        }
    }
}
