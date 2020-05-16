package com.kdu.user.application

import com.kdu.user.presentation.dto.UserInputDataRequestDto
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
}