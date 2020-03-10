package com.kdu.life.domain

import java.time.Year

enum class MaskPurchaseDay(
        private val firstNumber: Int,
        private val secondNumber: Int,
        val realName: String
) {
    MON(1, 6, "월"),
    TUE(2, 7, "화"),
    WED(3, 8, "수"),
    THU(4, 9, "목"),
    FRI(5, 0, "금"),
    EMPTY(-1, -1, "");

    private fun match(lastYearNumber: Int): Boolean {
        return this.firstNumber == lastYearNumber || this.secondNumber == lastYearNumber
    }

    companion object {
        fun find(year: Year): MaskPurchaseDay {
            val convertLastYearNumber = convertLastYearNumber(year)

            for (day in values()) {
                if (day.match(convertLastYearNumber)) {
                    return day
                }
            }
            return EMPTY
        }

        fun convertLastYearNumber(year: Year): Int {
            return year.value % 10
        }

    }
}