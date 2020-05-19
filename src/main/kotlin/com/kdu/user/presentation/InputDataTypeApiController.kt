package com.kdu.user.presentation

import com.kdu.user.application.InputDataTypeInternalService
import com.kdu.user.application.InputDataTypeService
import com.kdu.user.presentation.dto.InputDataTypeRequestDto
import com.kdu.user.presentation.dto.InputDataTypeResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class InputDataTypeApiController(
        private val inputDataTypeInternalService: InputDataTypeInternalService,
        private val inputDataTypeService: InputDataTypeService
) {

    @PostMapping("/api/admin/user/data/type")
    fun save(inputDataTypeRequestDto: InputDataTypeRequestDto): ResponseEntity<Long> {
        val id = inputDataTypeInternalService.save(inputDataTypeRequestDto)
        return ResponseEntity.ok(id)
    }

    @GetMapping("/api/admin/user/data/type")
    fun findAll(): ResponseEntity<List<InputDataTypeResponseDto>> {
        val result = inputDataTypeService.findAll()
        return ResponseEntity.ok(result)
    }

    @DeleteMapping("/api/user/data/type/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        inputDataTypeInternalService.delete(id)
        return ResponseEntity.ok().build()
    }
}