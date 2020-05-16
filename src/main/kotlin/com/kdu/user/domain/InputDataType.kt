package com.kdu.user.domain

import javax.persistence.*

@Entity
data class InputDataType private constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        val type: String,
        @Column(unique = true)
        val synonym: String
) {
    constructor(type: String, synonym: String) : this(null, type, synonym)
}