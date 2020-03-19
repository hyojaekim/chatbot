package com.kdu.common.util

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class SSLConnectTest {

    @Test
    internal fun `정상적으로 Document를 가져온다`() {
        val document = SSLConnect.getDocument("C08")
        Assertions.assertThat(document).isNotNull
    }
}