package com.kdu.life.application

import com.kdu.life.domain.MaskPurchaseDay
import com.kdu.life.ui.dto.MaskPurchaseInfoRequestDto
import org.springframework.stereotype.Service

@Service
class MaskService {

    fun findDay(maskPurchaseInfoRequestDto: MaskPurchaseInfoRequestDto): String {
        val year = maskPurchaseInfoRequestDto.year
        val maskPurchaseDay: MaskPurchaseDay = MaskPurchaseDay.find(year)
        return maskPurchaseDay.realName
    }
}