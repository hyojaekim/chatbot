package com.kdu.user.domain

import javax.persistence.*

@Entity
data class UserInputData private constructor(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        val text: String,
        var count: Int,
        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "input_data_type_id")
        var inputDataType: InputDataType?
) {
    constructor(text: String, inputDataType: InputDataType?) : this(null, text, 1, inputDataType)

    fun input() {
        this.count++
    }
}