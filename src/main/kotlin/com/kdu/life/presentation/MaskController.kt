package com.kdu.life.presentation

import com.kdu.common.message.ResponseMessage
import com.kdu.life.application.MaskService
import com.kdu.life.presentation.dto.MaskPurchaseInfoRequestDto
import com.kdu.life.util.MaskInfoMessageConverter
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MaskController(val maskService: MaskService) {

    @PostMapping("/api/mask")
    fun maskInfo(maskPurchaseInfoRequestDto: MaskPurchaseInfoRequestDto): ResponseEntity<String> {
        val day = maskService.findDay(maskPurchaseInfoRequestDto)
        val maskInfoMessage = MaskInfoMessageConverter.maskPurchaseRequestMessage(day)

        return ResponseEntity.ok(ResponseMessage.Builder()
                .simpleText(maskInfoMessage)
                .build()
                .toString())
    }
}