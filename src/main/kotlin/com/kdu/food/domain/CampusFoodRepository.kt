package com.kdu.food.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CampusFoodRepository : JpaRepository<CampusFood, Long> {

    fun findByCafeteriaAndDate(cafeteria: Cafeteria, date: String): Optional<CampusFood>
}