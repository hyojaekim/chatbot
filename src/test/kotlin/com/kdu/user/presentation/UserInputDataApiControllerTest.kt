package com.kdu.user.presentation

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class UserInputDataApiControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @Test
    internal fun `정상적으로 데이터 타입을 저장한다`() {
        webTestClient.post()
                .uri("/api/user/data/type")
                .body(BodyInserters.fromFormData("type", "abc")
                        .with("synonym", "test"))
                .exchange()
                .expectStatus().isOk
    }

    @Test
    internal fun `정상적으로 데이터 타입을 삭제한다`() {
        webTestClient.delete()
                .uri("/api/user/data/type/1")
                .exchange()
                .expectStatus().isOk
    }
}