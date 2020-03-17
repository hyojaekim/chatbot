package com.kdu.food.application

import com.kdu.food.exception.CampusFoodCrawlingFailException
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension

@ExtendWith(MockitoExtension::class)
internal class CampusFoodServiceTest {

    @Mock
    lateinit var campusFoodInternalService: CampusFoodInternalService

    @InjectMocks
    lateinit var campusFoodService: CampusFoodService

    @Test
    internal fun `잘못된 코드로 요청하면 예외가 발생한다`() {
        assertThrows(CampusFoodCrawlingFailException::class.java) {
            campusFoodService.saveFoods("C2")

            verify(campusFoodInternalService, times(0)).saveFoods(any())
        }
    }

    @Test
    internal fun `정상적으로 크롤링 후 저장한다`() {
        campusFoodService.saveFoods("C04")

        verify(campusFoodInternalService, times(1)).saveFoods(any())
    }
}