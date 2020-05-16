package com.kdu.user.application

import com.kdu.user.presentation.dto.UserInputDataRequestDto
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertNotNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UserInputDataServiceTest {

    @Autowired
    private lateinit var userInputDataService: UserInputDataService

    @Test
    internal fun `정상적으로 유저가 입력한 데이터를 저장한다`() {
        val requestDto = UserInputDataRequestDto("hello world!")
        assertNotNull(userInputDataService.save(requestDto))
    }
}