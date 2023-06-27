package com.jeonghyeon00.kotlin.carrot.module.controller

import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardReq
import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardPageRes
import com.jeonghyeon00.kotlin.carrot.module.entity.Board
import com.jeonghyeon00.kotlin.carrot.module.global.security.GetIdFromToken
import com.jeonghyeon00.kotlin.carrot.module.service.BoardService
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
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
    fun getBoards(@PageableDefault pageable: Pageable): Page<BoardPageRes> {
        return boardService.getBoards(pageable)
    }

    @DeleteMapping("/board/{boardId}")
    fun getBoard(@PathVariable(name = "boardId") boardId: Long): Board {
        return boardService.getBoard(boardId)
    }

    @DeleteMapping("/board/{boardId}")
    fun deleteBoard(@GetIdFromToken userId: String, @PathVariable(name = "boardId") boardId: Long): Boolean {
        return boardService.deleteBoard(userId, boardId)
    }
}
