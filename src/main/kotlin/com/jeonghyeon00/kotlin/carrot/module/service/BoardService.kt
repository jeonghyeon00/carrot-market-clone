package com.jeonghyeon00.kotlin.carrot.module.service

import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardReq
import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardReq.Companion.toBoard
import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardRes
import com.jeonghyeon00.kotlin.carrot.module.entity.Board
import com.jeonghyeon00.kotlin.carrot.module.global.exception.BaseException
import com.jeonghyeon00.kotlin.carrot.module.global.exception.BaseExceptionCode
import com.jeonghyeon00.kotlin.carrot.module.repository.BoardRepository
import com.jeonghyeon00.kotlin.carrot.module.repository.RegionRepository
import com.jeonghyeon00.kotlin.carrot.module.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class BoardService(
    private val boardRepository: BoardRepository,
    private val userRepository: UserRepository,
    private val regionRepository: RegionRepository,
) {
    @Transactional
    fun postBoard(userId: String, regionNumber: Int, boardReq: BoardReq): Board {
        val user = userRepository.findByIdOrNull(userId) ?: throw BaseException(BaseExceptionCode.USER_NOT_FOUND)
        return boardRepository.save(boardReq.toBoard(user, user.regions[regionNumber]))
    }

    @Transactional
    fun getBoards(pageable: Pageable): Page<BoardRes> {
        return boardRepository.findAll(pageable).map {
            BoardRes.toBoardRes(it)
        }
    }
}
