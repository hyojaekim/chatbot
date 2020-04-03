package com.kdu.life.util

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MaskInfoMessageConverterTest {

    @Test
    internal fun `정상적으로 마스크 5부제 메세지를 만든다`() {
        val result = MaskInfoMessageConverter.maskPurchaseRequestMessage("금")
        assertThat(result).contains("금요일")
    }
}