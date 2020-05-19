package com.kdu.user.application

import com.kdu.user.domain.UserInputData
import com.kdu.user.presentation.dto.UserInputDataRequestDto
import com.kdu.user.presentation.dto.UserInputDataResponseDto
import org.springframework.stereotype.Service

@Service
class UserInputDataService(
        private val userInputDataInternalService: UserInputDataInternalService,
        private val inputDataTypeInternalService: InputDataTypeInternalService
) {

    fun save(userInputDataRequestDto: UserInputDataRequestDto): Long {
        val text = userInputDataRequestDto.text
        val maybeInputDataType = inputDataTypeInternalService.findByContainsData(text)
        return userInputDataInternalService.save(text, maybeInputDataType).id!!
    }

    fun findAll(): List<UserInputDataResponseDto> {
        val userInputData: List<UserInputData> = userInputDataInternalService.findAll()
        return userInputData.map { u ->
            val inputDataType = u.inputDataType
            val type: String? = inputDataType?.type
            val synonym: String? = inputDataType?.synonym
            UserInputDataResponseDto(u.id!!, u.text, type, synonym, u.count)
        }
    }
}