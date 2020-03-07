package com.kdu.user.presentation

import com.kdu.util.JsonFactory
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserInfoApiControllerTest(@Autowired val webTestClient: WebTestClient) {

    @Test
    fun `정상적으로 유저의 캠퍼스를 저장한다`() {
        val result = webTestClient.post()
                .uri("/api/user/info/campus")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(JsonFactory.createUserInfo("testId", "고성"))
                .exchange()
                .expectStatus().isOk
                .expectBody(String::class.java)
                .returnResult().responseBody

        assertThat(result).contains("고성 캠퍼스")
    }

    @Test
    internal fun `잘못된 캠퍼스 정보로 요청하는 경우 실패한다`() {
        webTestClient.post()
                .uri("/api/user/info/campus")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(JsonFactory.createUserInfo("testId", "notExistCampus"))
                .exchange()
                .expectStatus().isBadRequest
    }
}