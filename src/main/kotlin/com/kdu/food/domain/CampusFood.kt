package com.kdu.food.domain

import java.time.LocalDate
import javax.persistence.*

@Entity
data class CampusFood(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long?,
        @Enumerated(EnumType.STRING)
        var cafeteria: Cafeteria,
        @Column(name = "day_of_week")
        var dayOfWeek: String,
        @Column(name = "start_date")
        var date: LocalDate?,
        @Column(name = "food_type")
        var type: String,
        @Column(name = "food_menu")
        var content: String
) {
    constructor() : this(null, Cafeteria.EMPTY, "", null, "", "")

    override fun toString(): String {
        return StringBuffer()
                .append(cafeteria.toString())
                .append(NEW_LINE)
                .append("$date ($dayOfWeek)")
                .append(NEW_LINE)
                .append("<$type>")
                .append(NEW_LINE)
                .append(content)
                .toString()
    }

    companion object {
        private const val NEW_LINE = "\n"

        fun of(cafeteria: Cafeteria, dayOfWeek: String, date: LocalDate, type: String, content: String): CampusFood {
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