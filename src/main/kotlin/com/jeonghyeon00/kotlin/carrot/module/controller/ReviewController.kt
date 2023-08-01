package com.jeonghyeon00.kotlin.carrot.module.controller

import com.jeonghyeon00.kotlin.carrot.module.dto.reviewDto.ReviewDto
import com.jeonghyeon00.kotlin.carrot.module.global.security.GetIdFromToken
import com.jeonghyeon00.kotlin.carrot.module.service.ReviewService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReviewController(
    private val reviewService: ReviewService,
) {
    @PostMapping("/board/{boardId}/review")
    fun postReview(
        @PathVariable(name = "boardId") boardId: Long,
        @RequestBody reviewDto: ReviewDto,
        @GetIdFromToken userId: String,
    ): Boolean {
        return reviewService.postReview(boardId, userId, reviewDto)
    }
}
