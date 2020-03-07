package com.kdu.user.domain

enum class Campus(val code: String, val realName: String) {
    GOSEONG("go", "고성"),
    MUNMAK("mun", "문막"),
    YANGJU("yang", "양주"),
    EMPTY("", "");

    companion object {
        fun find(code: String): Campus {
            for (campus in values()) {
                if (campus.code == code) {
                    return campus
                }
            }
            return EMPTY
        }
    }
}