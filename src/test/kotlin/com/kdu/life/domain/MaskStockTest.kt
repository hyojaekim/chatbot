package com.kdu.life.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

internal class MaskStockTest {

    @Test
    internal fun `정상적으로 MaskStock을 생성한다`() {
        val item = MaskStock("test", "test", "test", "some", "test", "01")

        assertNotNull(item)
        assertThat(item.title()).contains("test").contains("약국")
        assertThat(item.toString()).contains("30개 이상")
    }

    @Test
    internal fun `재고 상태가 없으면 빈값이 들어간다`() {
        val emptyItem = MaskStock("test", "test", "test", "empty", "test", "01")

        assertTrue(emptyItem.isEmpty())
    }
}