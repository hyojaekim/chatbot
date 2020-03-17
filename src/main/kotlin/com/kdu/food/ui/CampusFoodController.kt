package com.kdu.food.ui

import com.kdu.food.application.CampusFoodService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class CampusFoodController(val campusFoodService: CampusFoodService) {

    @PostMapping("/api/food/campus")
    fun save(@RequestParam code: String): ResponseEntity<Unit> {
        campusFoodService.saveFoods(code)
        return ResponseEntity.ok().build()
    }
}