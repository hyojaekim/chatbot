package com.kdu.user.domain.repository

import com.kdu.user.domain.EventUser
import org.springframework.data.jpa.repository.JpaRepository

interface EventUserRepository : JpaRepository<EventUser, Long> {
    fun countAllByWinnerTrue(): Int
    fun existsByKakaoId(kakaoId: String): Boolean
}