package com.kdu.user.presentation.dto

class UserInputDataResponseDto(
        val id: Long,
        val text: String,
        val type: String?,
        val synonym: String?,
        val count: Int
) {
}