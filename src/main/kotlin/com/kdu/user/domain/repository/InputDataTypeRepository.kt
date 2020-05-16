package com.kdu.user.domain.repository

import com.kdu.user.domain.InputDataType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface InputDataTypeRepository : JpaRepository<InputDataType, Long> {

    @Query(value = "select * from input_data_type where :text like concat('%', synonym, '%')", nativeQuery = true)
    fun findTypeWithSynonym(@Param("text") text: String): InputDataType?

    fun existsBySynonym(synonym: String): Boolean

    fun findBySynonym(synonym: String): InputDataType
}