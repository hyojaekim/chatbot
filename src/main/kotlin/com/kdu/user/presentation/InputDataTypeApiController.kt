package com.kdu.user.presentation

import com.kdu.user.application.InputDataTypeInternalService
import com.kdu.user.presentation.dto.InputDataTypeRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user/data/type")
class InputDataTypeApiController(private val inputDataTypeInternalService: InputDataTypeInternalService) {

    @PostMapping
    fun save(inputDataTypeRequestDto: InputDataTypeRequestDto): ResponseEntity<Void> {
        inputDataTypeInternalService.save(inputDataTypeRequestDto)
        return ResponseEntity.ok().build()
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        inputDataTypeInternalService.delete(id)
        return ResponseEntity.ok().build()
    }
}