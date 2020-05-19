package com.kdu.user.application

import com.kdu.user.presentation.dto.InputDataTypeRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class InputDataTypeInternalServiceTest {

    @Autowired
    private lateinit var inputDatatypeInternalService: InputDataTypeInternalService

    @Test
    internal fun `정상적으로 저장한다`() {
        val requestDto = InputDataTypeRequestDto("type", "test")

        assertNotNull(inputDatatypeInternalService.save(requestDto))
    }

    @Test
    internal fun `정상적으로 삭제한다`() {
        val requestDto = InputDataTypeRequestDto("type", "test")
        val id = inputDatatypeInternalService.save(requestDto)

        assertTrue(inputDatatypeInternalService.delete(id))
        assertFalse(inputDatatypeInternalService.delete(id))
    }

    @Test
    internal fun `텍스트에 동의어가 포함되면 해당 타입을 리턴한다`() {
        val requestDto = InputDataTypeRequestDto("type", "test")
        val id = inputDatatypeInternalService.save(requestDto)

        assertThat(inputDatatypeInternalService.findByContainsData("This is test")!!.id).isEqualTo(id)
        assertThat(inputDatatypeInternalService.findByContainsData("test good!")!!.id).isEqualTo(id)
        assertThat(inputDatatypeInternalService.findByContainsData("good test hahaha")!!.id).isEqualTo(id)
        assertNull(inputDatatypeInternalService.findByContainsData("This is Test"))
    }

    @Test
    internal fun `모든 타입과 동의어들을 가져온다`() {
        val result = inputDatatypeInternalService.findAll()

        assertNotNull(result)
    }
}