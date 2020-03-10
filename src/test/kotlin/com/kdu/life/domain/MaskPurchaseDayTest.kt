package com.kdu.life.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Year

internal class MaskPurchaseDayTest {

    @Test
    internal fun `정상적으로 마스크 구매일을 가져온다`() {
        assertThat(MaskPurchaseDay.find(Year.of(1991))).isEqualTo(MaskPurchaseDay.MON)
        assertThat(MaskPurchaseDay.find(Year.of(1992))).isEqualTo(MaskPurchaseDay.TUE)
        assertThat(MaskPurchaseDay.find(Year.of(1993))).isEqualTo(MaskPurchaseDay.WED)
        assertThat(MaskPurchaseDay.find(Year.of(1994))).isEqualTo(MaskPurchaseDay.THU)
        assertThat(MaskPurchaseDay.find(Year.of(1995))).isEqualTo(MaskPurchaseDay.FRI)

        assertThat(MaskPurchaseDay.find(Year.of(1996))).isEqualTo(MaskPurchaseDay.MON)
        assertThat(MaskPurchaseDay.find(Year.of(1997))).isEqualTo(MaskPurchaseDay.TUE)
        assertThat(MaskPurchaseDay.find(Year.of(1998))).isEqualTo(MaskPurchaseDay.WED)
        assertThat(MaskPurchaseDay.find(Year.of(1999))).isEqualTo(MaskPurchaseDay.THU)
        assertThat(MaskPurchaseDay.find(Year.of(1990))).isEqualTo(MaskPurchaseDay.FRI)
    }

    @Test
    internal fun `정상적으로 년도의 끝자리를 가져온다`() {
        val year: Year = Year.of(1991)

        assertThat(MaskPurchaseDay.convertLastYearNumber(year)).isEqualTo(1)
    }
}