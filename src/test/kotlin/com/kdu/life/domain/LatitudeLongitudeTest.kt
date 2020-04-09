package com.kdu.life.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

internal class LatitudeLongitudeTest {

    @Test
    internal fun `정상적으로 위도와 경도를 가져온다`() {
        val location = LatitudeLongitude.Location(30.52, 50.24)
        val geometry = LatitudeLongitude.Geometry(location)
        val results = LatitudeLongitude.Result(geometry)

        val latitudeLongitude = LatitudeLongitude(arrayListOf(results), "OK")
        assertNotNull(latitudeLongitude)

        val result = latitudeLongitude.get()
        assertThat(result.latitude).isEqualTo(30.52)
        assertThat(result.longitude).isEqualTo(50.24)
    }

    @Test
    internal fun `정상적인 상태가 아니면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            LatitudeLongitude(arrayListOf(), "NotFound")
        }
    }
}