package com.kdu.life.application

import com.kdu.life.ui.dto.MaskPurchaseInfoRequestDto
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Year

internal class MaskServiceTest(val maskService: MaskService) {

    @Test
    internal fun `정상적으로 마스크 구매일을 알 수 있다`() {
        val maskPurchaseInfoRequestDto = MaskPurchaseInfoRequestDto(Year.of(1991))

        assertThat(maskService.findDay(maskPurchaseInfoRequestDto)).isEqualTo("월")
    }
}