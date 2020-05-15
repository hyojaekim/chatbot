package com.kdu.user.presentation

import com.kdu.user.application.InputDataTypeInternalService
import com.kdu.user.presentation.dto.InputDataTypeRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class InputDataTypeApiController(private val inputDataTypeInternalService: InputDataTypeInternalService) {

    @PostMapping("/api/user/data/type")
    fun save(inputDataTypeRequestDto: InputDataTypeRequestDto): ResponseEntity<Void> {
        inputDataTypeInternalService.save(inputDataTypeRequestDto)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/api/user/data/type/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        inputDataTypeInternalService.delete(id)
        return ResponseEntity.ok().build()
    }
}