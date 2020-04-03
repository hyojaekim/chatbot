package com.kdu.life.presentation

import com.kdu.util.JsonFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class SubwayControllerTest(@Autowired val webTestClient: WebTestClient) {

    @Test
    internal fun `정상적으로 지하철 도착정보를 가져온다`() {
        val responseBody = webTestClient.post()
                .uri("/api/subway")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(JsonFactory.createParams("station", "의정부"))
                .exchange()
                .expectStatus().isOk
                .expectBody(String::class.java)
                .returnResult().responseBody
    }
}