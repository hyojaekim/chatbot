package com.kdu.food.presentation

import com.kdu.util.JsonFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class CampusFoodControllerTest(@Autowired val webTestClient: WebTestClient) {

    @Test
    internal fun `정상적으로 학식 메뉴를 크롤링하여 저장한다`() {
        val code = "C04"
        webTestClient.post()
                .uri("/api/food/campus?code=$code")
                .exchange()
                .expectStatus().isOk
    }

    @Test
    internal fun `잘못된 코드로 요청하는 경우 BAD REQUEST`() {
        val code = "~~"
        webTestClient.post()
                .uri("/api/food/campus?code=$code")
                .exchange()
                .expectStatus().isBadRequest
    }

    @Test
    internal fun `정상적으로 학식 메뉴를 가져온다`() {
        val result = webTestClient.post()
                .uri("/api/food/campus/search")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(JsonFactory.createParams("campusCode", "C04"))
                .exchange()
                .expectStatus().isOk
                .expectBody(String::class.java)
                .returnResult().responseBody

        assertThat(result).contains("테스트 메뉴")
    }

    @Test
    internal fun `학식 메뉴가 없으면 정해지지 않았다고 메세지를 보낸다`() {
        val result = webTestClient.post()
                .uri("/api/food/campus/search")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(JsonFactory.createParams("campusCode", "C01"))
                .exchange()
                .expectStatus().isOk
                .expectBody(String::class.java)
                .returnResult().responseBody

        assertThat(result).contains("현재 메뉴가 정해지지 않았습니다.")
    }
}