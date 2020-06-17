package com.kdu.user.application

import com.kdu.user.exception.LoginFailException
import com.kdu.user.presentation.dto.KakaoInfoResponseDto
import com.kdu.user.presentation.dto.LoginRequestDto
import org.springframework.core.env.Environment
import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import org.springframework.util.MultiValueMap
import org.springframework.web.client.RestTemplate

@Service
class LoginService(val restTemplate: RestTemplate, val environment: Environment) {

    fun isAdmin(loginRequestDto: LoginRequestDto): Boolean {
        val kakaoInfoResponseDto = requestKakaoInfo(loginRequestDto) ?: throw LoginFailException()
        return isAdmin(kakaoInfoResponseDto)
    }

    fun isAdmin(kakaoInfoResponseDto: KakaoInfoResponseDto): Boolean {
        val adminID = environment.getProperty("kakao.admin.id") ?: throw LoginFailException()
        return kakaoInfoResponseDto.id == adminID
    }

    fun requestKakaoInfo(loginRequestDto: LoginRequestDto): KakaoInfoResponseDto? {
        val accessToken = loginRequestDto.accessToken
        val headers = HttpHeaders()
        headers.add("Authorization", "bearer $accessToken")
        val httpEntity = HttpEntity<MultiValueMap<String, String>>(headers)
        val result = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.POST,
                httpEntity,
                KakaoInfoResponseDto::class.java
        )
        return result.body
    }

    fun getAuthorities(isAdmin: Boolean): List<GrantedAuthority> {
        val authorities = arrayListOf<GrantedAuthority>()
        authorities.add(SimpleGrantedAuthority("ROLE_USER"))
        if (isAdmin) {
            authorities.add(SimpleGrantedAuthority("ROLE_ADMIN"))
        }
        return authorities
    }
}
