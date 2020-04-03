package com.kdu.life.application

import com.kdu.life.presentation.dto.MaskPurchaseInfoRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Year

internal class MaskServiceTest {

    @Test
    internal fun `정상적으로 마스크 구매일을 알 수 있다`() {
        val maskService = MaskService()
        val maskPurchaseInfoRequestDto = MaskPurchaseInfoRequestDto(Year.of(1991))

        assertThat(maskService.findDay(maskPurchaseInfoRequestDto)).isEqualTo("월")
    }
}