package com.kdu.food.domain

import javax.persistence.*

@Entity
data class CampusFood(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        @Enumerated
        var cafeteria: Cafeteria,
        var dayOfWeek: String,
        var date: String,
        var type: String,
        var content: String
) {
    constructor() : this(null, Cafeteria.EMPTY, "", "", "", "")

    companion object {
        fun of(cafeteria: Cafeteria, dayOfWeek: String, date: String, type: String, content: String): CampusFood {
            val campusFood = CampusFood()
            campusFood.cafeteria = cafeteria
            campusFood.dayOfWeek = dayOfWeek
            campusFood.date = date
            campusFood.type = type
            campusFood.content = content
            return campusFood
        }
    }
}