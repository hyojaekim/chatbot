package com.kdu.food.domain

import com.kdu.user.domain.Campus
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CafeteriaTest {

    @Test
    internal fun `캠퍼스 코드로 Cafeteria를 찾는다`() {
        assertThat(Cafeteria.findByCode("C01")).isEqualTo(Cafeteria.GOSEONG)
        assertThat(Cafeteria.findByCode("C02")).isEqualTo(Cafeteria.GOSEONG_GIRL_DORMITORY)
        assertThat(Cafeteria.findByCode("C03")).isEqualTo(Cafeteria.GOSEONG_MAN_DORMITORY)
        assertThat(Cafeteria.findByCode("C04")).isEqualTo(Cafeteria.YANGJU)
        assertThat(Cafeteria.findByCode("C05")).isEqualTo(Cafeteria.MUNMAK)
        assertThat(Cafeteria.findByCode("C06")).isEqualTo(Cafeteria.MUNMAK_HAPPY_SHARE)
        assertThat(Cafeteria.findByCode("C07")).isEqualTo(Cafeteria.YANGJU_HAPPY_SHARE)
        assertThat(Cafeteria.findByCode("c08")).isEqualTo(Cafeteria.GOSEONG_GIRL_STUDENT_DORMITORY)
        assertThat(Cafeteria.findByCode("~~")).isEqualTo(Cafeteria.EMPTY)
    }

    @Test
    internal fun `정상적으로 이름을 가져온다`() {
        assertThat(Cafeteria.GOSEONG.toString()).contains("고성").contains("캠퍼스 식당")
    }

    @Test
    internal fun `해당 지역에 있는 식당 이름을 가져온다`() {
        val result = Cafeteria.findByCampus(Campus.YANGJU)

        assertThat(result.size).isEqualTo(2)
    }
}