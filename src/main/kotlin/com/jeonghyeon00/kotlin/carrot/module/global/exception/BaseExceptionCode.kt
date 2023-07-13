package com.jeonghyeon00.kotlin.carrot.module.global.exception

import org.springframework.http.HttpStatus

enum class BaseExceptionCode(val httpStatusCode: Int, val message: String) {
    // 400 BAD REQUEST
    BAD_REQUEST(HttpStatus.BAD_REQUEST.value(), "잘못된 요청입니다."),
    REFRESH_TOKEN_MISMATCH(HttpStatus.BAD_REQUEST.value(), "리프레쉬 토큰이 일치하지 않습니다."),
    AUTHORIZATION_HEADER_NULL(HttpStatus.BAD_REQUEST.value(), "인증 헤더가 null입니다."),
    DUPLICATE_REGION_OR_OVER_TWO_REGIONS(HttpStatus.BAD_REQUEST.value(), "이미 등록된 지역이거나 지역이 2개입니다."),

    // 403 FORBIDDEN
    NOT_YOUR_BOARD(HttpStatus.FORBIDDEN.value(), "작성자가 아닙니다."),
    NOT_YOUR_REGION(HttpStatus.FORBIDDEN.value(), "본인의 지역의 게시글이 아닙니다."),
    NOT_YOUR_CHATROOM(HttpStatus.FORBIDDEN.value(), "당신의 채팅방이 아닙니다."),

    // 404 NOT FOUND
    USER_NOT_FOUND(HttpStatus.NOT_FOUND.value(), "아이디가 존재하지 않습니다."),

    // 409 CONFLICT
    USER_ID_CONFLICT(HttpStatus.CONFLICT.value(), "아이디가 중복입니다."),
    DUPLICATE_WISHLIST(HttpStatus.CONFLICT.value(), "이미 위시리스트에 존재합니다."),
}
