package com.jeonghyeon00.kotlin.carrot.module.service

import com.jeonghyeon00.kotlin.carrot.module.constants.BoardStatus
import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardReq
import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardReq.Companion.toBoard
import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardPageRes
import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardRes
import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.WishListDto
import com.jeonghyeon00.kotlin.carrot.module.dto.imageDto.ImageReq.Companion.toImage
import com.jeonghyeon00.kotlin.carrot.module.entity.Board
import com.jeonghyeon00.kotlin.carrot.module.entity.WishList
import com.jeonghyeon00.kotlin.carrot.module.global.exception.BaseException
import com.jeonghyeon00.kotlin.carrot.module.global.exception.BaseExceptionCode
import com.jeonghyeon00.kotlin.carrot.module.repository.*
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
    private val wishListRepository: WishListRepository,

) {
    @Transactional
    fun postBoard(userId: String, regionNumber: Int, boardReq: BoardReq): Board {
        val user = userRepository.findByIdOrNull(userId) ?: throw BaseException(BaseExceptionCode.USER_NOT_FOUND)
        val board = boardRepository.save(boardReq.toBoard(user, user.regions[regionNumber]))
        board.images.addAll(
            boardReq.images.map {
                it.toImage(board)
            },
        )
        return board
    }

    @Transactional
    fun getBoards(userId: String, pageable: Pageable): Page<BoardPageRes> {
        val regions = userRepository.findByIdOrNull(userId)?.regions
        return boardRepository.findAllByRegionIn(regions!!, pageable).map {
            BoardPageRes.toBoardPageRes(it)
        }
    }

    @Transactional
    fun deleteBoard(userId: String, boardId: Long): Boolean {
        val board = boardRepository.getReferenceById(boardId)
        if (board.seller.userId == userId) {
            boardRepository.delete(board)
            return true
        } else {
            throw BaseException(BaseExceptionCode.NOT_YOUR_BOARD)
        }
    }

    @Transactional
    fun getBoard(userId: String, boardId: Long): BoardRes {
        val user = userRepository.getReferenceById(userId)
        val board = boardRepository.getReferenceById(boardId)
        if (user.regions.contains(board.region)) {
            board.viewCount++
            return BoardRes.toBoardRes(board)
        } else {
            throw BaseException(BaseExceptionCode.NOT_YOUR_REGION)
        }
    }

    @Transactional
    fun patchBoard(userId: String, boardId: Long, boardReq: BoardReq): Boolean {
        val board = boardRepository.getReferenceById(boardId)
        if (board.seller.userId == userId) {
            with(board) {
                category = boardReq.category
                title = boardReq.title
                description = boardReq.description
                price = boardReq.price
                images.clear()
                images.addAll(
                    boardReq.images.map {
                        it.toImage(board)
                    },
                )
            }
        } else {
            throw BaseException(BaseExceptionCode.NOT_YOUR_BOARD)
        }
        return true
    }

    @Transactional
    fun addWishList(userId: String, boardId: Long): Boolean {
        val board = boardRepository.getReferenceById(boardId)
        val user = userRepository.getReferenceById(userId)
        if (wishListRepository.existsByBoardAndUser(board, user)) {
            throw BaseException(BaseExceptionCode.DUPLICATE_WISHLIST)
        }
        wishListRepository.save(WishList(user, board))
        return true
    }

    @Transactional
    fun deleteWishList(userId: String, boardId: Long): Boolean {
        val board = boardRepository.getReferenceById(boardId)
        val user = userRepository.getReferenceById(userId)
        wishListRepository.deleteByBoardAndUser(board, user)
        return true
    }

    @Transactional
    fun reservation(userId: String, boardId: Long): Boolean {
        val board = boardRepository.getReferenceById(boardId)
        if (board.boardStatus != BoardStatus.COMPLETE && isUsersBoard(userId, board)) {
            board.boardStatus = BoardStatus.RESERVATION
        } else {
            throw BaseException(BaseExceptionCode.ALREADY_COMPLETE)
        }
        return true
    }

    @Transactional
    fun complete(userId: String, boardId: Long, buyerId: String): Boolean {
        val board = boardRepository.getReferenceById(boardId)
        if (isUsersBoard(userId, board)) {
            board.boardStatus = BoardStatus.COMPLETE
            board.buyer = userRepository.getReferenceById(buyerId)
        } else {
            throw BaseException(BaseExceptionCode.NOT_YOUR_BOARD)
        }
        return true
    }

    @Transactional
    fun getWishList(userId: String): List<WishListDto> {
        return wishListRepository.getAllByUser(userRepository.getReferenceById(userId)).map {
            WishListDto.toDto(it)
        }
    }

    fun isUsersBoard(userId: String, board: Board): Boolean {
        return board.seller.userId == userId
    }
}
