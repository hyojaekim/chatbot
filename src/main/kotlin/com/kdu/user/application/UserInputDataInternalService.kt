package com.kdu.user.application

import com.kdu.user.domain.InputDataType
import com.kdu.user.domain.UserInputData
import com.kdu.user.domain.repository.UserInputDataRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class UserInputDataInternalService(private val userInputDataRepository: UserInputDataRepository) {

    fun save(text: String, inputDataType: InputDataType?): UserInputData {
        var userInputData = userInputDataRepository.findByText(text)
        userInputData?.let { userInputData!!.input() }
        userInputData = userInputData ?: UserInputData(text, inputDataType)
        return userInputDataRepository.save(userInputData)
    }

    @Transactional(readOnly = true)
    fun findAll(): List<UserInputData> {
        return userInputDataRepository.findAll()
    }
}