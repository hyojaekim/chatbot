package com.kdu.food.application

import com.kdu.food.domain.Cafeteria
import com.kdu.food.domain.CampusFoods
import com.kdu.food.exception.CampusFoodCrawlingFailException
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CampusFoodCrawlingServiceTest(@Autowired val crawlingService: CampusFoodCrawlingService) {

    @Test
    internal fun `정상적으로 1주일 학식 메뉴를 크롤링한다`() {
        val result: ArrayList<CampusFoods> = crawlingService.crawling(Cafeteria.YANGJU)

        assertThat(result).isNotNull
        if (result.size != 0) {
            assertThat(result[0].get().size).isEqualTo(7)
        }
    }

    @Test
    internal fun `존재하지 않는 코드로 요청하면 예외가 발생한다`() {
        Assertions.assertThrows(CampusFoodCrawlingFailException::class.java) {
            crawlingService.crawling(Cafeteria.EMPTY)
        }
    }
}