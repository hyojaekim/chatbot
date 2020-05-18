package com.kdu.user.presentation

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class InputDataTypeApiControllerTest {

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
        val id = webTestClient.post()
                .uri("/api/user/data/type")
                .body(BodyInserters.fromFormData("type", "developer")
                        .with("synonym", "dev"))
                .exchange()
                .expectStatus().isOk
                .expectBody(Long::class.java)
                .returnResult().responseBody!!

        webTestClient.delete()
                .uri("/api/user/data/type/$id")
                .exchange()
                .expectStatus().isOk
    }
}