package com.kdu.life.ui

import com.kdu.util.JsonFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MaskControllerTest(@Autowired val webTestClient: WebTestClient) {

    @Test
    fun `정상적으로 마스크 구매일을 알 수 있다`() {
        val result = webTestClient.post()
                .uri("/api/mask")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(JsonFactory.createMaskInfo("1991"))
                .exchange()
                .expectStatus().isOk
                .expectBody(String::class.java)
                .returnResult().responseBody

        assertThat(result).contains("월요일")
    }
}