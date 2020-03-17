package com.kdu.food.domain

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class CafeteriaTest {

    @Test
    internal fun `캠퍼스 코드로 Cafeteria를 찾는다`() {
        Assertions.assertThat(Cafeteria.findByCode("C01")).isEqualTo(Cafeteria.GOSEONG)
        Assertions.assertThat(Cafeteria.findByCode("C02")).isEqualTo(Cafeteria.GOSEONG_GIRL_DORMITORY)
        Assertions.assertThat(Cafeteria.findByCode("C03")).isEqualTo(Cafeteria.GOSEONG_MAN_DORMITORY)
        Assertions.assertThat(Cafeteria.findByCode("C04")).isEqualTo(Cafeteria.YANGJU)
        Assertions.assertThat(Cafeteria.findByCode("C05")).isEqualTo(Cafeteria.MUNMAK)
        Assertions.assertThat(Cafeteria.findByCode("C06")).isEqualTo(Cafeteria.MUNMAK_HAPPY_SHARE)
        Assertions.assertThat(Cafeteria.findByCode("C07")).isEqualTo(Cafeteria.YANGJU_HAPPY_SHARE)
        Assertions.assertThat(Cafeteria.findByCode("c08")).isEqualTo(Cafeteria.GOSEONG_GIRL_STUDENT_DORMITORY)
        Assertions.assertThat(Cafeteria.findByCode("~~")).isEqualTo(Cafeteria.EMPTY)
    }

    @Test
    internal fun `정상적으로 이름을 가져온다`() {
        Assertions.assertThat(Cafeteria.GOSEONG.toString()).contains("고성").contains("캠퍼스 식당")
    }
}