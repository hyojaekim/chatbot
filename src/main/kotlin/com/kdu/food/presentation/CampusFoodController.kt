package com.kdu.food.presentation

import com.kdu.common.message.ResponseMessage
import com.kdu.food.application.CampusFoodService
import com.kdu.food.application.dto.CampusFoodRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/food/campus")
class CampusFoodController(private val campusFoodService: CampusFoodService) {

    @PostMapping("/search")
    fun find(campusFoodRequestDto: CampusFoodRequestDto): ResponseEntity<String> {
        val message = campusFoodService.find(campusFoodRequestDto)
        return ResponseEntity.ok(ResponseMessage.Builder()
                .simpleText(message)
                .build()
                .toString())
    }

    @PostMapping
    fun save(@RequestParam code: String): ResponseEntity<Unit> {
        campusFoodService.saveFoods(code)
        return ResponseEntity.ok().build()
    }
}