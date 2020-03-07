package com.kdu.user.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CampusTest {

    @Test
    internal fun `정상적으로 고성 캠퍼스를 찾는다`() {
        val result = Campus.find("고성")

        assertThat(result).isEqualTo(Campus.GOSEONG)
    }

    @Test
    internal fun `정상적으로 양주 캠퍼스를 찾는다`() {
        val result = Campus.find("양주")

        assertThat(result).isEqualTo(Campus.YANGJU)
    }

    @Test
    internal fun `정상적으로 문막 캠퍼스를 찾는다`() {
        val result = Campus.find("문막")

        assertThat(result).isEqualTo(Campus.MUNMAK)
    }

    @Test
    internal fun `존재하지 않는 이름이면 EMPTY를 반환한다`() {
        val result = Campus.find("?")

        assertThat(result).isEqualTo(Campus.EMPTY)
    }
}