package com.kdu.user.application

import com.kdu.user.domain.InputDataType
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class InputDataTypeServiceTest {

    @Mock
    private lateinit var inputDataTypeInternalService: InputDataTypeInternalService

    @InjectMocks
    private lateinit var inputDataTypeService: InputDataTypeService

    @Test
    internal fun `정상적으로 모든 데이터의 타입과 동의어들을 가져온다`() {
        val inputDataType = InputDataType("test type", "test")
        inputDataType.id = 1

        whenever(inputDataTypeInternalService.findAll()).thenReturn(listOf(inputDataType))

        val result = inputDataTypeService.findAll()

        assertThat(result.size).isEqualTo(1)
        assertThat(result[0].type).isEqualTo(inputDataType.type)
        assertThat(result[0].synonym).isEqualTo(inputDataType.synonym)
    }
}