package com.kdu.life.util

import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

internal class UriFactoryTest {

    @Test
    internal fun `정상적으로 URI를 생성한다`() {
        assertNotNull(UriFactory.createSubwayUri("key", "의정부"))
    }
}