package com.kdu.common.util

import com.google.gson.JsonElement
import com.google.gson.JsonParser
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

internal class KduJsonParserTest {

    lateinit var body: String
    lateinit var jsonElement: JsonElement

    @BeforeEach
    internal fun setUp() {
        body = "{\"action\":{\"params\":{\"campus\":\"양주\"}},\"userRequest\":{\"user\":{\"id\":\"test\"}}}"
        jsonElement = JsonParser.parseString(body)
    }

    @Test
    internal fun `정상적으로 파라미를 가져온다`() {
        val result = KduJsonParser.findParams(jsonElement, "campus")

        assertNotNull(result)
        assertThat(result).isEqualTo("양주")
    }

    @Test
    internal fun `정상적으로 유저의 ID를 가져온다`() {
        val result = KduJsonParser.findUserId(jsonElement)

        assertNotNull(result)
        assertThat(result).isEqualTo("test")
    }
}