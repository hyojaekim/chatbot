package com.kdu.user.application

import com.kdu.user.presentation.dto.InputDataTypeResponseDto
import org.springframework.stereotype.Service

@Service
class InputDataTypeService(private val inputDataTypeInternalService: InputDataTypeInternalService) {

    fun findAll(): List<InputDataTypeResponseDto> {
        val result = inputDataTypeInternalService.findAll()
        return result.map { data ->
            InputDataTypeResponseDto(data.id!!, data.type, data.synonym)
        }
    }
}