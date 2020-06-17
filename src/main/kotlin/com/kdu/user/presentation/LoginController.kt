package com.kdu.user.presentation

import com.kdu.user.application.LoginService
import com.kdu.user.exception.LoginFailException
import com.kdu.user.presentation.dto.LoginRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletRequest


@RestController
class LoginController(private val loginService: LoginService) {

    @PostMapping("/api/login")
    fun login(loginRequestDto: LoginRequestDto, request: HttpServletRequest): ResponseEntity<Boolean> {
        val kakaoInfo = loginService.requestKakaoInfo(loginRequestDto) ?: throw LoginFailException()
        val admin = loginService.isAdmin(kakaoInfo)
        val authorities = loginService.getAuthorities(admin)
        val authentication = UsernamePasswordAuthenticationToken(kakaoInfo.id, kakaoInfo.id, authorities)
        val securityContext = SecurityContextHolder.getContext()
        securityContext.authentication = authentication

        val session = request.getSession(true)
        session.setAttribute("SPRING_SECURITY_CONTEXT", securityContext)
        return ResponseEntity.ok(admin)
    }
}