package com.kdu.user.presentation

import com.kdu.common.message.ResponseMessage
import com.kdu.user.application.KduEventService
import com.kdu.user.presentation.dto.QrCodeRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class EventController(private val kduEventService: KduEventService) {

    @PostMapping("/api/event")
    fun kduEvent(qrCodeRequestDto: QrCodeRequestDto): ResponseEntity<String> {
        val eventResult = kduEventService.getEventResult(qrCodeRequestDto)
        return ResponseEntity.ok(ResponseMessage.Builder()
                .simpleText(eventResult)
                .build()
                .toString())
    }
}