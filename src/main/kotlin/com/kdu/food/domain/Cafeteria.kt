package com.kdu.food.domain

enum class Cafeteria(private val location: String, private val realName: String, val code: String) {
    YANGJU("양주", "캠퍼스 식당", "C04"),
    YANGJU_HAPPY_SHARE("양주", "행복공공기숙사 식당", "C07"),
    MUNMAK("문막", "캠퍼스 식당", "C05"),
    MUNMAK_HAPPY_SHARE("문막", "행복공공기숙사 식당", "C06"),
    GOSEONG("고성", "캠퍼스 식당", "C01"),
    GOSEONG_GIRL_DORMITORY("고성", "숭례원 식당", "C02"),
    GOSEONG_GIRL_STUDENT_DORMITORY("고성", "숭례원 학생식당", "c08"),
    GOSEONG_MAN_DORMITORY("고성", "양현원 식당", "C03"),
    EMPTY("", "", "");

    override fun toString(): String {
        return "[$location] $realName"
    }

    companion object {
        fun findByCode(code: String): Cafeteria {
            for (cafeteria in values()) {
                if (cafeteria.code == code) {
                    return cafeteria
                }
            }
            return EMPTY
        }
    }
}