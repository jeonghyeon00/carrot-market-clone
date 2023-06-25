package com.jeonghyeon00.kotlin.carrot.module.controller

import com.jeonghyeon00.kotlin.carrot.module.dto.boardDto.BoardReq
import com.jeonghyeon00.kotlin.carrot.module.entity.Board
import com.jeonghyeon00.kotlin.carrot.module.global.security.GetIdFromToken
import com.jeonghyeon00.kotlin.carrot.module.service.BoardService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/board")
class BoardController(
    private val boardService: BoardService,
) {
    @PostMapping("")
    fun postBoard(
        @GetIdFromToken userId: String,
        @RequestBody boardReq: BoardReq,
        @RequestParam(name = "regionNumber") regionNumber: Int,
    ): Board {
        return boardService.postBoard(userId, regionNumber, boardReq)
    }
}
