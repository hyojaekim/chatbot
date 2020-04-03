package com.kdu.life.presentation

import com.kdu.common.message.ResponseMessage
import com.kdu.life.application.SubwayService
import com.kdu.life.presentation.dto.SubwayInfoRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/subway")
class SubwayController(val subwayService: SubwayService) {

    @PostMapping
    fun find(subwayInfoRequestDto: SubwayInfoRequestDto): ResponseEntity<String> {
        val subwayInfo = subwayService.find(subwayInfoRequestDto)
        return ResponseEntity.ok(ResponseMessage.Builder()
                .basicCards(subwayInfo)
                .build()
                .toString())
    }
}