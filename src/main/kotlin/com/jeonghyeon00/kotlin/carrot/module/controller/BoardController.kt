package com.jeonghyeon00.kotlin.carrot.module.controller

import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardReq
import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardPageRes
import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardRes
import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.WishListDto
import com.jeonghyeon00.kotlin.carrot.module.entity.Board
import com.jeonghyeon00.kotlin.carrot.module.global.security.GetIdFromToken
import com.jeonghyeon00.kotlin.carrot.module.service.BoardService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class BoardController(
    private val boardService: BoardService,
) {
    @PostMapping("/board")
    fun postBoard(
        @GetIdFromToken userId: String,
        @RequestBody boardReq: BoardReq,
        @RequestParam(name = "regionNumber") regionNumber: Int,
    ): Board {
        return boardService.postBoard(userId, regionNumber, boardReq)
    }

    @GetMapping("/boards")
    fun getBoards(@GetIdFromToken userId: String, @PageableDefault pageable: Pageable): Page<BoardPageRes> {
        return boardService.getBoards(userId, pageable)
    }

    @GetMapping("/board/{boardId}") // 지역 반영
    fun getBoard(@GetIdFromToken userId: String, @PathVariable(name = "boardId") boardId: Long): BoardRes {
        return boardService.getBoard(userId, boardId)
    }

    @DeleteMapping("/board/{boardId}")
    fun deleteBoard(@GetIdFromToken userId: String, @PathVariable(name = "boardId") boardId: Long): Boolean {
        return boardService.deleteBoard(userId, boardId)
    }

    @PatchMapping("/board/{boardId}")
    fun patchBoard(
        @GetIdFromToken userId: String,
        @PathVariable(name = "boardId") boardId: Long,
        @RequestBody boardReq: BoardReq,
    ): Boolean {
        return boardService.patchBoard(userId, boardId, boardReq)
    }

    @PostMapping("/board/{boardId}/wishList")
    fun addWishList(@GetIdFromToken userId: String, @PathVariable(name = "boardId") boardId: Long): Boolean {
        return boardService.addWishList(userId, boardId)
    }

    @GetMapping("/board/wishList")
    fun getWishList(@GetIdFromToken userId: String): List<WishListDto> {
        return boardService.getWishList(userId)
    }

    @DeleteMapping("/board/{boardId}/wishList")
    fun deleteWishList(@GetIdFromToken userId: String, @PathVariable(name = "boardId") boardId: Long): Boolean {
        return boardService.deleteWishList(userId, boardId)
    }

    @PostMapping("/board/{boardId}/reservation")
    fun reservation(@GetIdFromToken userId: String, @PathVariable(name = "boardId") boardId: Long): Boolean {
        return boardService.reservation(userId, boardId)
    }

    @PostMapping("/board/{boardId}/complete")
    fun complete(
        @GetIdFromToken userId: String,
        @PathVariable(name = "boardId") boardId: Long,
        @RequestParam buyerId: String,
    ): Boolean {
        return boardService.complete(userId, boardId, buyerId)
    }
}
