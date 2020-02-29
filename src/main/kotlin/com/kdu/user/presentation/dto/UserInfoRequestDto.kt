package com.kdu.user.presentation.dto

import com.kdu.user.domain.Campus

class UserInfoRequestDto(
        val kakaoId: String,
        val campus: Campus
)