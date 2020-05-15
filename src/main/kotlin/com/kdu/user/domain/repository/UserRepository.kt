package com.kdu.user.domain.repository

import com.kdu.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<User, Long> {
    fun findByKakaoId(kakaoId: String): User?
    fun existsByKakaoId(kakaoId: String): Boolean
}