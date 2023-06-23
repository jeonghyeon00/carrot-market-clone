package com.jeonghyeon00.kotlin.carrot.module.service

import com.jeonghyeon00.kotlin.carrot.module.global.exception.BaseException
import com.jeonghyeon00.kotlin.carrot.module.global.exception.BaseExceptionCode
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
        if (!user.regions.contains(region) && user.regions.count() < 2) {
            user.regions.add(region)
        } else {
            throw BaseException(BaseExceptionCode.DUPLICATE_REGION_OR_OVER_TWO_REGIONS)
        }
        return true
    }

    @Transactional
    fun deleteRegion(userId: String, regionNumber: Int): Boolean {
        val user = userRepository.getReferenceById(userId)
        user.regions.removeAt(regionNumber)
        return true
    }
}
