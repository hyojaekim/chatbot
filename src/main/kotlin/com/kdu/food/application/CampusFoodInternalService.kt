package com.kdu.food.application

import com.kdu.food.domain.Cafeteria
import com.kdu.food.domain.CampusFood
import com.kdu.food.domain.CampusFoodRepository
import com.kdu.food.domain.CampusFoods
import com.kdu.food.exception.NotFoundCampusFoodException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDate

@Service
@Transactional
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

    @Transactional(readOnly = true)
    fun findByCafeteriaAndDate(cafeteria: Cafeteria, date: LocalDate): CampusFood {
        return campusFoodRepository.findByCafeteriaAndDate(cafeteria, date.toString())
                .orElseThrow { NotFoundCampusFoodException() }
    }
}