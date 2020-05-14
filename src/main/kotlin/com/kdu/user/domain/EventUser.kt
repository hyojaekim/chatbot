package com.kdu.user.domain

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class EventUser(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        val kakaoId: String,
        var winner: Boolean
) {
    constructor(kakaoId: String) : this(null, kakaoId, Math.random() < 0.35)
}