package com.kdu.user.presentation

import com.kdu.util.JsonFactory
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserInfoApiControllerTest(@Autowired val webTestClient: WebTestClient) {

    @Test
    fun `정상적으로 유저의 캠퍼스를 저장한다`() {
        webTestClient.post()
                .uri("/api/user/info/campus")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(JsonFactory.createUserInfo("testId", "go"))
                .exchange()
                .expectStatus().isOk
    }
}