package com.kdu.common.controller

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class EnvironmentControllerTest(@Autowired val testRestTemplate: TestRestTemplate) {

    @Test
    fun `정상적으로 현재 profile을 가져온다`() {
        val result = testRestTemplate.getForObject("/api/profile", String::class.java)

        assertThat(result).isEqualTo("local")
    }
}