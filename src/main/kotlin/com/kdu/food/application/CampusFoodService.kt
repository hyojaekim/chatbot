package com.kdu.food.application

import com.kdu.food.application.dto.CampusFoodRequestDto
import com.kdu.food.domain.Cafeteria
import com.kdu.food.domain.CampusFood
import com.kdu.food.exception.NotFoundCafeteriaException
import org.springframework.stereotype.Service

@Service
class CampusFoodService(private val campusFoodInternalService: CampusFoodInternalService) {

    fun saveFoods(code: String) {
        val cafeteria = getCafeteria(code)
        campusFoodInternalService.saveFoods(cafeteria)
    }

    private fun getCafeteria(code: String): Cafeteria {
        val cafeteria = Cafeteria.findByCode(code)
        if (cafeteria == Cafeteria.EMPTY) {
            throw NotFoundCafeteriaException()
        }
        return cafeteria
    }

    fun find(requestDto: CampusFoodRequestDto): String {
        val cafeteria = getCafeteria(requestDto.code)
        val campusFood: CampusFood = campusFoodInternalService.findByCafeteriaAndDate(cafeteria, requestDto.date)
        return campusFood.toString()
    }
}