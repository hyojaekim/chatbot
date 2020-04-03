package com.kdu.life.application

import com.kdu.life.domain.Subway
import com.kdu.life.domain.SubwayErrorMessage
import com.kdu.life.domain.SubwayInfo
import com.kdu.life.presentation.dto.SubwayInfoRequestDto
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class SubwayServiceTest {

    @Mock
    lateinit var subwayInternalService: SubwayInternalService

    @InjectMocks
    lateinit var subwayService: SubwayService

    @Test
    internal fun `정상적으로 지하철 정보를 찾는다`() {
        whenever(subwayInternalService.find(any())).thenReturn(
                SubwayInfo(SubwayErrorMessage(200), arrayListOf(Subway("의정부", null, "test", "test"))))

        val result = subwayService.find(SubwayInfoRequestDto("의정부"))

        verify(subwayInternalService, times(1)).find(any())
        assertThat(result.size).isEqualTo(1)
    }
}