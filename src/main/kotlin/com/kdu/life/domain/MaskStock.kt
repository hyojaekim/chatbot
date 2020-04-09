package com.kdu.life.domain

data class MaskStock(
        val addr: String,
        val created_at: String?,
        val name: String,
        var remain_stat: String?,
        val stock_at: String?,
        var type: String
) {
    init {
        if (!this.isEmpty()) {
            this.remain_stat = RemainState.message(this.remain_stat!!)
            this.type = StoreType.realName(this.type)
        }
    }

    fun isEmpty(): Boolean {
        return created_at.isNullOrEmpty() || remain_stat.isNullOrEmpty() || stock_at.isNullOrEmpty()
    }

    fun title(): String {
        return "$name [$type]"
    }

    override fun toString(): String {
        return StringBuffer().append("[$addr]")
                .append(NEW_LINE + NEW_LINE)
                .append("[재고 상태] $remain_stat")
                .append(NEW_LINE + NEW_LINE)
                .append("[입고 날짜] $stock_at")
                .append(NEW_LINE)
                .append("[업데이트 날짜] $stock_at")
                .toString()
    }

    companion object {
        private const val NEW_LINE = "\n"
    }
}

/**
 * plenty: 100개 이상
 * some: 30개 이상
 * few: 2개 이상 30개 미만
 * empty: 1개 이하
 * break: 판매 중지
 *
 * empty, break 상태는 취급하지 않는다.
 */
private enum class RemainState(
        private val code: String,
        private val message: String
) {
    PLENTY("plenty", "100개 이상"),
    SOME("some", "30개 이상"),
    FEW("few", "2개 이상 30개 미만"),
    EMPTY("", "");

    companion object {
        fun message(code: String): String {
            for (value in values()) {
                if (value.code == code) {
                    return value.message
                }
            }
            return EMPTY.message
        }
    }
}

/**
 * 01: 약국
 * 02: 우체국
 * 03: 농협
 */
private enum class StoreType(
        private val type: String,
        private val realName: String
) {
    PHARMACY("01", "약국"),
    POST_OFFICE("02", "우체국"),
    NH("03", "농협"),
    EMPTY("", "");

    companion object {
        fun realName(type: String): String {
            for (value in values()) {
                if (value.type == type) {
                    return value.realName
                }
            }
            return EMPTY.realName
        }
    }
}