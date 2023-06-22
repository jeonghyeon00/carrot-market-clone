package com.jeonghyeon00.kotlin.carrot.module.service

import com.jeonghyeon00.kotlin.carrot.module.repository.RegionRepository
import com.jeonghyeon00.kotlin.carrot.module.repository.UserRepository
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class RegionService(private val regionRepository: RegionRepository, private val userRepository: UserRepository) {
    @Transactional
    fun addRegion(userId: String, regionId: Long): Boolean {
        val user = userRepository.getReferenceById(userId)
        val region = regionRepository.getReferenceById(regionId)
        user.regions.add(region)
        return true
    }
}
