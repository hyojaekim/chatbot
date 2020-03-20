package com.kdu.food.ui

import com.kdu.common.message.ResponseMessage
import com.kdu.food.application.CampusFoodService
import com.kdu.food.application.dto.CampusFoodRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/food/campus")
class CampusFoodController(private val campusFoodService: CampusFoodService) {

    @GetMapping
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