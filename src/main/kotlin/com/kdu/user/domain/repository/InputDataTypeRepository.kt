package com.kdu.user.domain.repository

import com.kdu.user.domain.InputDataType
import org.springframework.data.jpa.repository.JpaRepository

interface InputDataTypeRepository : JpaRepository<InputDataType, Long> {
}