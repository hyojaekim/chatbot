package com.kdu.life.application

import com.kdu.common.message.BasicCard
import com.kdu.life.domain.MaskPurchaseDay
import com.kdu.life.domain.MaskStockInfo
import com.kdu.life.exception.MaskStockRequestFailException
import com.kdu.life.presentation.dto.MaskPurchaseInfoRequestDto
import com.kdu.life.presentation.dto.MaskStockRequestDto
import org.springframework.stereotype.Service
import kotlin.streams.toList

@Service
class MaskService(val maskInternalService: MaskInternalService) {

    fun findDay(maskPurchaseInfoRequestDto: MaskPurchaseInfoRequestDto): String {
        val year = maskPurchaseInfoRequestDto.year
        val maskPurchaseDay: MaskPurchaseDay = MaskPurchaseDay.find(year)
        return maskPurchaseDay.realName
    }

    fun findMaskStockInfo(maskStockRequestDto: MaskStockRequestDto): List<BasicCard> {
        try {
            val maskStockInfo: MaskStockInfo = maskInternalService.requestMaskStockInfo(maskStockRequestDto)
            return maskStockInfo.stores.stream()
                    .map { maskStock -> BasicCard(maskStock.title(), maskStock.toString()) }
                    .toList()
        } catch (e: Exception) {
            throw MaskStockRequestFailException()
        }
    }
}