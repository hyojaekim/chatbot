package com.kdu.food.application

import com.kdu.food.domain.Cafeteria
import com.kdu.food.domain.CampusFood
import com.kdu.food.domain.CampusFoodRepository
import com.kdu.food.domain.CampusFoods
import com.kdu.food.exception.NotFoundCampusFoodException
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate
import java.util.*

@ExtendWith(MockitoExtension::class)
internal class CampusFoodInternalServiceTest {

    @Mock
    lateinit var campusFoodCrawlingService: CampusFoodCrawlingService

    @Mock
    lateinit var campusFoodRepository: CampusFoodRepository

    @InjectMocks
    lateinit var campusFoodInternalService: CampusFoodInternalService

    @Test
    internal fun `정상적으로 크롤링 후에 저장한다`() {
        val campusFood = CampusFood.of(Cafeteria.YANGJU, "월", "2020-01-02", "type", "content")
        val campusFoods = CampusFoods(arrayListOf(campusFood))
        val allCampusFoods = arrayListOf(campusFoods)

        whenever(campusFoodCrawlingService.crawling(any())).thenReturn(allCampusFoods)

        campusFoodInternalService.saveFoods(Cafeteria.YANGJU)

        verify(campusFoodCrawlingService, times(1)).crawling(Cafeteria.YANGJU)
        verify(campusFoodRepository, times(1)).save(campusFood)
    }

    @Test
    internal fun `학식 메뉴를 찾지 못하는 경우 예외가 발생한다`() {
        whenever(campusFoodRepository.findByCafeteriaAndDate(any(), any())).thenReturn(Optional.empty())

        assertThrows<NotFoundCampusFoodException> { campusFoodInternalService.findByCafeteriaAndDate(Cafeteria.YANGJU, LocalDate.now()) }
    }

    @Test
    internal fun `정상적으로 학식 메뉴를 가져온다`() {
        whenever(campusFoodRepository.findByCafeteriaAndDate(any(), any())).thenReturn(Optional.of(CampusFood()))

        assertDoesNotThrow {
            campusFoodInternalService.findByCafeteriaAndDate(Cafeteria.YANGJU, LocalDate.now())
        }
    }
}