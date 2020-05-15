package com.kdu.user.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class InputDataType private constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        val type: String,
        val synonym: String
) {
    constructor(type: String, synonym: String) : this(null, type, synonym)
}