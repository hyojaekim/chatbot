package com.kdu.user.presentation

import com.kdu.user.application.InputDataTypeInternalService
import com.kdu.user.presentation.dto.InputDataTypeRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserInputDataApiController(private val inputDataTypeInternalService: InputDataTypeInternalService) {

    @PostMapping("/api/user/data/type")
    fun save(inputDataTypeRequestDto: InputDataTypeRequestDto): ResponseEntity<Void> {
        inputDataTypeInternalService.save(inputDataTypeRequestDto)
        return ResponseEntity.ok().build()
    }
}