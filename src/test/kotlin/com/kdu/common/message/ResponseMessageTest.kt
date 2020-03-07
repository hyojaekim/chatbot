package com.kdu.common.message

import com.google.gson.JsonObject
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ResponseMessageTest {

    @Test
    internal fun `정상적으로 SimpleText를 생성한다`() {
        val result: JsonObject = ResponseMessage.Builder()
                .simpleText("Hello")
                .build()

        assertThat(result.toString()).contains("Hello")
    }
}