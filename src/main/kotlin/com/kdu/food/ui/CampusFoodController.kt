package com.kdu.food.ui

import com.kdu.common.message.ResponseMessage
import com.kdu.food.application.CampusFoodService
import com.kdu.food.application.dto.CampusFoodRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate

@RestController
@RequestMapping("/api/food/campus")
class CampusFoodController(private val campusFoodService: CampusFoodService) {

    @PostMapping("/search")
    fun find(@RequestParam code: String): ResponseEntity<String> {
        val campusFoodRequestDate = CampusFoodRequestDto(code, LocalDate.now())
        val message = campusFoodService.find(campusFoodRequestDate)
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