package com.kdu.life.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class MaskStockInfoTest {

    @Test
    internal fun `정상적으로 마스크 재고 정보를 생성한다`() {
        val item1 = MaskStock("test", "test", "test", "some", "test", "01")
        val item2 = MaskStock("test", "test", "test", "some", "test", "01")

        val result = MaskStockInfo(5, arrayListOf(item1, item2))

        assertNotNull(result)
        assertThat(result.stores.size).isEqualTo(2)
    }

    @Test
    internal fun `재고가 없는 가게는 제외한다`() {
        val item = MaskStock("test", "test", "test", "some", "test", "01")
        val emptyItem = MaskStock("test", "test", "test", "", "", "01")

        val result = MaskStockInfo(5, arrayListOf(item, emptyItem))

        assertNotNull(result)
        assertThat(result.stores.size).isEqualTo(1)
    }
}