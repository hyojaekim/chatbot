package com.kdu.user.application

import com.kdu.user.domain.InputDataType
import com.kdu.user.domain.repository.InputDataTypeRepository
import com.kdu.user.presentation.dto.InputDataTypeRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class InputDataTypeInternalService(private val inputDataTypeRepository: InputDataTypeRepository) {

    fun save(inputDataTypeRequestDto: InputDataTypeRequestDto): Long {
        if (inputDataTypeRepository.existsBySynonym(inputDataTypeRequestDto.synonym)) {
            return inputDataTypeRepository.findBySynonym(inputDataTypeRequestDto.synonym).id!!
        }
        val inputDataType = InputDataType(inputDataTypeRequestDto.type, inputDataTypeRequestDto.synonym)
        return inputDataTypeRepository.save(inputDataType).id!!
    }

    fun delete(id: Long): Boolean {
        if (inputDataTypeRepository.existsById(id)) {
            inputDataTypeRepository.deleteById(id)
            return true
        }
        return false
    }

    @Transactional(readOnly = true)
    fun findByContainsData(text: String): InputDataType? {
        return inputDataTypeRepository.findTypeWithSynonym(text)
    }
}