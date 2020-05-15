package com.kdu.user.application

import com.kdu.user.domain.InputDataType
import com.kdu.user.domain.repository.InputDataTypeRepository
import com.kdu.user.presentation.dto.InputDataTypeRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InputDataTypeInternalService(private val inputDataTypeRepository: InputDataTypeRepository) {

    fun save(inputDataTypeRequestDto: InputDataTypeRequestDto) {
        val inputDataType = InputDataType(inputDataTypeRequestDto.type, inputDataTypeRequestDto.synonym)
        inputDataTypeRepository.save(inputDataType)
    }
}