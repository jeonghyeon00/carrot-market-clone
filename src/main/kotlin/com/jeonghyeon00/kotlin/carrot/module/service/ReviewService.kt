package com.jeonghyeon00.kotlin.carrot.module.service

import com.jeonghyeon00.kotlin.carrot.module.dto.reviewDto.ReviewDto
import com.jeonghyeon00.kotlin.carrot.module.entity.Review
import com.jeonghyeon00.kotlin.carrot.module.global.exception.BaseException
import com.jeonghyeon00.kotlin.carrot.module.global.exception.BaseExceptionCode
import com.jeonghyeon00.kotlin.carrot.module.repository.BoardRepository
import com.jeonghyeon00.kotlin.carrot.module.repository.ReviewRepository
import com.jeonghyeon00.kotlin.carrot.module.repository.UserRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class ReviewService(
    private val reviewRepository: ReviewRepository,
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository,
) {
    @Transactional
    fun postReview(boardId: Long, userId: String, reviewDto: ReviewDto): Boolean {
        val board = boardRepository.getReferenceById(boardId)
        val user = userRepository.getReferenceById(userId)
        if (board.buyer == user && !reviewRepository.existsByBoardAndBoardBuyer(board, user)) {
            reviewRepository.save(
                Review(
                    board,
                    reviewDto.satisfaction,
                ),
            ).also {
                user.temperature += reviewDto.satisfaction.temperature
            }
            return true
        } else {
            throw BaseException(BaseExceptionCode.ALREADY_COMPLETE)
        }
    }
}
