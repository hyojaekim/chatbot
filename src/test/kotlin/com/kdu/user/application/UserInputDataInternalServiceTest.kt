package com.kdu.user.application

import com.kdu.user.domain.InputDataType
import com.kdu.user.domain.repository.InputDataTypeRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UserInputDataInternalServiceTest {

    @Autowired
    private lateinit var inputDataTypeRepository: InputDataTypeRepository

    @Autowired
    private lateinit var userInputDataInternalService: UserInputDataInternalService

    @Test
    internal fun `정상적으로 유저가 입력한 데이터를 저장한다`() {
        val text = "hello world"
        val inputDataType = inputDataTypeRepository.save(InputDataType("hi", "hello"))
        val savedUserInputData = userInputDataInternalService.save(text, inputDataType)

        assertThat(savedUserInputData.text).isEqualTo(text)
        assertThat(savedUserInputData.inputDataType).isEqualTo(inputDataType)
    }

    @Test
    internal fun `이미 저장된 데이터인 경우 count가 증가한다`() {
        val text = "hello world"
        val inputDataType = inputDataTypeRepository.save(InputDataType("hi", "hello2"))
        userInputDataInternalService.save(text, inputDataType)
        val savedUserInputData = userInputDataInternalService.save(text, inputDataType)

        assertTrue(savedUserInputData.count > 1)
    }
}