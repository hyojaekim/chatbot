package com.kdu.food.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CampusFoodTest {

    @Test
    internal fun `정상적으로 객체를 생성한다`() {
        val campusFood = CampusFood.of(Cafeteria.GOSEONG, "월", "2020-01-02", "type", "content")
        assertThat(campusFood).isNotNull
        assertThat(campusFood.cafeteria).isEqualTo(Cafeteria.GOSEONG)
        assertThat(campusFood.dayOfWeek).isEqualTo("월")
        assertThat(campusFood.date).isEqualTo("2020-01-02")
        assertThat(campusFood.type).isEqualTo("type")
        assertThat(campusFood.content).isEqualTo("content")
    }
}