package com.kdu.user.domain

enum class Campus(val code: String) {
    GOSEONG("go"),
    MUNMAK("mun"),
    YANGJU("yang"),
    EMPTY("");

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