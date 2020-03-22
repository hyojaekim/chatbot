package com.kdu.food.application

import com.kdu.food.application.dto.CampusFoodRequestDto
import com.kdu.food.domain.CampusFood
import com.kdu.food.exception.NotFoundCafeteriaException
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import java.time.LocalDate

@ExtendWith(MockitoExtension::class)
internal class CampusFoodServiceTest {

    @Mock
    lateinit var campusFoodInternalService: CampusFoodInternalService

    @InjectMocks
    lateinit var campusFoodService: CampusFoodService

    @Test
    internal fun `잘못된 코드로 요청하면 예외가 발생한다`() {
        assertThrows(NotFoundCafeteriaException::class.java) {
            campusFoodService.saveFoods("C2")

            verify(campusFoodInternalService, times(0)).saveFoods(any())
        }
    }

    @Test
    internal fun `정상적으로 크롤링 후 저장한다`() {
        campusFoodService.saveFoods("C04")

        verify(campusFoodInternalService, times(1)).saveFoods(any())
    }

    @Test
    internal fun `캠퍼스 코드가 비정상적이면 예외가 발생한다`() {
        val requestDto = CampusFoodRequestDto("~~", LocalDate.now())

        assertThrows<NotFoundCafeteriaException> { campusFoodService.find(requestDto) }
    }

    @Test
    internal fun `정상적으로 학식 메뉴를 가져온다`() {
        val requestDto = CampusFoodRequestDto("C04", LocalDate.now())

        whenever(campusFoodInternalService.findByCafeteriaAndDate(any(), any())).thenReturn(CampusFood())

        campusFoodService.find(requestDto)

        verify(campusFoodInternalService, times(1)).findByCafeteriaAndDate(any(), any())
    }
}