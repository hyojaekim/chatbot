package com.kdu.user.presentation

import com.kdu.user.presentation.dto.UserInputDataResponseDto
import com.kdu.util.JsonFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import kotlin.test.assertNotNull

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UserInputDataApiControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    internal fun `정상적으로 유저가 입력한 데이터를 저장한다`() {
        webTestClient.post()
                .uri("/api/user/data")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(JsonFactory.createParams("text", "hello world!"))
                .exchange()
                .expectStatus().isOk
    }

    @Test
    internal fun `정상적으로 모든 데이터를 조회한다`() {
        val result = webTestClient.get()
                .uri("/api/user/data")
                .exchange()
                .expectStatus().isOk
                .expectBodyList(UserInputDataResponseDto::class.java)
                .returnResult().responseBody

        result?.forEach { d -> assertNotNull(d.id) }
    }
}