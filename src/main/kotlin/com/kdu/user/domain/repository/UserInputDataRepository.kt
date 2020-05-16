package com.kdu.user.domain.repository

import com.kdu.user.domain.UserInputData
import org.springframework.data.jpa.repository.JpaRepository

interface UserInputDataRepository : JpaRepository<UserInputData, Long> {
    fun findByText(text: String): UserInputData?
}