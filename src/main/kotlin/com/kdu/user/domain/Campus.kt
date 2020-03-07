package com.kdu.user.domain

enum class Campus(val realName: String) {
    GOSEONG("고성"),
    MUNMAK("문막"),
    YANGJU("양주"),
    EMPTY("");

    companion object {
        fun find(realName: String): Campus {
            for (campus in values()) {
                if (campus.realName == realName) {
                    return campus
                }
            }
            return EMPTY
        }
    }
}