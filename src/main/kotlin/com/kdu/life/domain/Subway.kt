package com.kdu.life.domain

data class Subway(
        val statnNm: String,
        var btrainSttus: String?,
        val trainLineNm: String,
        val arvlMsg2: String
) {
    init {
        btrainSttus = if (btrainSttus == null) "" else "(${btrainSttus})"
    }

    fun getStationName(): String {
        return "[${statnNm}역]${NEW_LINE}${btrainSttus}"
    }

    companion object {
        private const val NEW_LINE = "\n"
    }

    override fun toString(): String {
        return StringBuffer()
                .append(trainLineNm)
                .append(NEW_LINE)
                .append(arvlMsg2)
                .append(NEW_LINE)
                .toString()
    }
}