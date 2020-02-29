package com.kdu.user.domain

import com.kdu.user.exception.NotFoundCampusException
import javax.persistence.*

@Entity
class User private constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        var kakaoId: String,
        @Enumerated(EnumType.STRING)
        var campus: Campus
) {
    constructor(kakaoId: String, campus: Campus) : this(null, kakaoId, campus)

    fun update(campus: Campus) {
        if (campus == Campus.EMPTY) {
            throw NotFoundCampusException()
        }
        this.campus = campus
    }
}