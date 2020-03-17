package com.kdu.food.application

import com.kdu.food.domain.Cafeteria
import com.kdu.food.domain.CampusFoodRepository
import com.kdu.food.domain.CampusFoods
import org.springframework.stereotype.Service

@Service
class CampusFoodInternalService(
        private val campusFoodCrawlingService: CampusFoodCrawlingService,
        private val campusFoodRepository: CampusFoodRepository
) {

    fun saveFoods(cafeteria: Cafeteria) {
        val crawlingCampusFoods = campusFoodCrawlingService.crawling(cafeteria)
        for (campusFoods in crawlingCampusFoods) {
            saveFoods(campusFoods)
        }
    }

    private fun saveFoods(campusFoods: CampusFoods) {
        for (campusFood in campusFoods.get()) {
            campusFoodRepository.save(campusFood)
        }
    }
}