package com.jeonghyeon00.kotlin.carrot.module.global.exception

import org.springframework.http.HttpStatus

enum class BaseExceptionCode(val httpStatusCode: Int, val message: String) {
    // 400 BAD REQUEST
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."),
    REFRESH_TOKEN_MISMATCH(HttpStatus.BAD_REQUEST.value(), "리프레쉬 토큰이 일치하지 않습니다."),

    // 409 CONFLICT
    USER_ID_CONFLICT(HttpStatus.CONFLICT.value(), "아이디가 중복입니다."),
}
