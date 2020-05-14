package com.kdu.user.domain

import org.springframework.data.jpa.repository.JpaRepository

interface EventUserRepository : JpaRepository<EventUser, Long> {
    fun countAllByWinnerTrue(): Int
    fun existsByKakaoId(kakaoId: String): Boolean
}