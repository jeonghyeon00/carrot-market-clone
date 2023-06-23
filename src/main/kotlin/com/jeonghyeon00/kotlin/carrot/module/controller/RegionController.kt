package com.jeonghyeon00.kotlin.carrot.module.controller

import com.jeonghyeon00.kotlin.carrot.module.global.security.GetIdFromToken
import com.jeonghyeon00.kotlin.carrot.module.service.RegionService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RegionController(private val regionService: RegionService) {
    @PostMapping("/region")
    fun addRegion(@GetIdFromToken userId: String, @RequestParam(name = "regionId") regionId: Long): Boolean {
        return regionService.addRegion(userId, regionId)
    }

    @DeleteMapping("/region/{regionNumber}")
    fun deleteRegion(@GetIdFromToken userId: String, @PathVariable(name = "regionNumber") regionNumber: Int): Boolean {
        return regionService.deleteRegion(userId, regionNumber)
    }
}