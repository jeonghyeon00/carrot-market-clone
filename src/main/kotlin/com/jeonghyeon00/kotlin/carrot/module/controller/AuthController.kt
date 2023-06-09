package com.jeonghyeon00.kotlin.carrot.module.controller

import com.jeonghyeon00.kotlin.carrot.module.dto.authDto.SignInDto
import com.jeonghyeon00.kotlin.carrot.module.dto.authDto.SignUpDto
import com.jeonghyeon00.kotlin.carrot.module.dto.authDto.TokenDto
import com.jeonghyeon00.kotlin.carrot.module.service.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/sign-up")
    fun signUp(@RequestBody signUpDto: SignUpDto): ResponseEntity<Boolean> {
        return ResponseEntity.ok().body(authService.signUp(signUpDto))
    }

    @PostMapping("/sign-in")
    fun signIn(@RequestBody signInDto: SignInDto): TokenDto {
        return authService.signIn(signInDto)
    }

    @PostMapping("/refresh")
    fun refresh(@RequestBody tokenDto: TokenDto): TokenDto {
        return authService.refresh(tokenDto)
    }
}
