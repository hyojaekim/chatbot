package com.kdu.life.application

import com.kdu.life.domain.MaskStock
import com.kdu.life.domain.MaskStockInfo
import com.kdu.life.exception.MaskStockRequestFailException
import com.kdu.life.presentation.dto.LocationDto
import com.kdu.life.presentation.dto.MaskPurchaseInfoRequestDto
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.Year
import kotlin.test.assertNotNull

@ExtendWith(MockitoExtension::class)
internal class MaskServiceTest {

    @Mock
    lateinit var maskInternalService: MaskInternalService

    @InjectMocks
    lateinit var maskService: MaskService

    @Test
    internal fun `정상적으로 마스크 구매일을 알 수 있다`() {
        val maskPurchaseInfoRequestDto = MaskPurchaseInfoRequestDto(Year.of(1991))

        assertThat(maskService.findDay(maskPurchaseInfoRequestDto)).isEqualTo("월")
    }

    @Test
    internal fun `정상적으로 위도와 경도로 마스크 재고 정보를 알 수 있다`() {
        val testMaskStock = MaskStock("test", "test", "test", "some", "test", "01")
        whenever(maskInternalService.requestMaskStockInfo(any()))
                .thenReturn(MaskStockInfo(1, arrayListOf(testMaskStock)))

        val result = maskService.findMaskStockInfo(LocationDto(31.0, 53.42))

        assertNotNull(result)
        assertThat(result.size).isEqualTo(1)
    }

    @Test
    internal fun `마스크 재고 정보를 요청할때 예외가 발생하는 경우`() {
        whenever(maskInternalService.requestMaskStockInfo(any())).thenThrow(MaskStockRequestFailException())

        assertThrows<MaskStockRequestFailException> {
            maskService.findMaskStockInfo(LocationDto(31.0, 53.42))
        }
    }
}