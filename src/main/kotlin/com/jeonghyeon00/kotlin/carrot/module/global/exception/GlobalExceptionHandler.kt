package com.jeonghyeon00.kotlin.carrot.module.global.exception

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(BaseException::class)
    fun baseExceptionHandler(e: BaseException): ResponseEntity<ExceptionResponse> {
        return ResponseEntity.status(e.baseExceptionCode.httpStatusCode)
            .body(ExceptionResponse(e.baseExceptionCode.httpStatusCode, e.baseExceptionCode.message))
    }
}
