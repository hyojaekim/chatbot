package com.kdu.user.application

import com.kdu.user.domain.Admin
import com.kdu.user.exception.LoginFailException
import com.kdu.user.presentation.dto.LoginRequestDto
import org.springframework.core.env.Environment
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class LoginService(val restTemplate: RestTemplate, val environment: Environment) {

    fun isAdmin(loginRequestDto: LoginRequestDto): Boolean {
        val currentUser = requestKakaoId(loginRequestDto) ?: throw LoginFailException()
        val adminID = environment.getProperty("kakao.admin.id") ?: throw LoginFailException()
        return currentUser.id == adminID
    }

    private fun requestKakaoId(loginRequestDto: LoginRequestDto): Admin? {
        val accessToken = loginRequestDto.accessToken
        val headers = HttpHeaders()
        headers.add("Authorization", "bearer $accessToken")
        val httpEntity = HttpEntity<MultiValueMap<String, String>>(headers)
        val result = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                Admin::class.java
        )
        return result.body
    }
}
