package com.kdu.user.application

import com.kdu.user.presentation.dto.UserInputDataRequestDto
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UserInputDataServiceTest {

    @Autowired
    private lateinit var userInputDataService: UserInputDataService

    @Test
    internal fun `정상적으로 유저가 입력한 데이터를 저장한다`() {
        val requestDto = UserInputDataRequestDto("hello world!")
        assertNotNull(userInputDataService.save(requestDto))
    }

    @Test
    internal fun `정상적으로 모든 유저의 데이터를 가져온다`() {
        userInputDataService.save(UserInputDataRequestDto("test1"))
        userInputDataService.save(UserInputDataRequestDto("test2"))
        userInputDataService.save(UserInputDataRequestDto("test3"))

        val result = userInputDataService.findAll()

        assertTrue(result.isNotEmpty())

        for (data in result) {
            assertNotNull(data.id)
            assertNotNull(data.text)
        }
    }
}