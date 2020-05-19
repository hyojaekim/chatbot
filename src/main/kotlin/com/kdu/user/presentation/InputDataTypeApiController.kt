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

    @PostMapping("/api/admin/user/data/type")
    fun save(inputDataTypeRequestDto: InputDataTypeRequestDto): ResponseEntity<Long> {
        val id = inputDataTypeInternalService.save(inputDataTypeRequestDto)
        return ResponseEntity.ok(id)
    }

    @DeleteMapping("/api/user/data/type/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        inputDataTypeInternalService.delete(id)
        return ResponseEntity.ok().build()
    }
}