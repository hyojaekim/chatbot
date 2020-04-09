package com.kdu.life.util

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class UriFactoryTest {

    @Test
    internal fun `정상적으로 지하철 도착 정보 URI를 생성한다`() {
        assertNotNull(UriFactory.createSubwayUri("key", "의정부"))
    }

    @Test
    internal fun `정상적으로 마스크 재고 정보 URI를 생성한다`() {
        assertNotNull(UriFactory.createMaskStockUri(37.7387295, 127.0437021))
    }

    @Test
    internal fun `정상적으로 주소 API URI를 생성한다`() {
        assertNotNull(UriFactory.createAddressUri("의정부역", "test"))
    }
}