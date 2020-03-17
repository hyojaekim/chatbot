package com.kdu.food.application

import com.kdu.food.domain.Cafeteria
import com.kdu.food.exception.CampusFoodCrawlingFailException
import org.springframework.stereotype.Service

@Service
class CampusFoodService(private val campusFoodInternalService: CampusFoodInternalService) {

    fun saveFoods(code: String) {
        val cafeteria = Cafeteria.findByCode(code)
        if (cafeteria == Cafeteria.EMPTY) {
            throw CampusFoodCrawlingFailException()
        }
        campusFoodInternalService.saveFoods(cafeteria)
    }
}