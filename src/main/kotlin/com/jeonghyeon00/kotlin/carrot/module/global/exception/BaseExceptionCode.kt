package com.jeonghyeon00.kotlin.carrot.module.global.exception

import org.springframework.http.HttpStatus

enum class BaseExceptionCode(val httpStatusCode: Int, val message: String) {
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."),

    USER_ID_CONFLICT(HttpStatus.CONFLICT.value(), "아이디가 중복입니다."),
}
