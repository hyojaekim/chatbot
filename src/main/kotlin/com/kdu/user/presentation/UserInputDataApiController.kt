package com.kdu.user.presentation

import com.kdu.user.application.UserInputDataService
import com.kdu.user.presentation.dto.UserInputDataRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user/data")
class UserInputDataApiController(private val userInputDataService: UserInputDataService) {

    @PostMapping
    fun saveUserInputData(userInputDataRequestDto: UserInputDataRequestDto): ResponseEntity<Void> {
        userInputDataService.save(userInputDataRequestDto)
        return ResponseEntity.ok().build()
    }
}