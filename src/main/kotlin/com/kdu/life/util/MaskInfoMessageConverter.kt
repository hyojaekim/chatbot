package com.kdu.life.util

class MaskInfoMessageConverter {

    companion object {
        fun maskPurchaseRequestMessage(day: String): String {
            return StringBuffer().append(day)
                    .append("요일에 구매 가능합니다.")
                    .append("\n\n")
                    .append("해당하는 요일에 구매 못하신 분은 주말(토,일)에 구매가 가능합니다.")
                    .toString()
        }
    }
}