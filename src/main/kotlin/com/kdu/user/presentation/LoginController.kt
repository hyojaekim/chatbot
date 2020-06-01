package com.kdu.user.presentation

import com.kdu.user.application.LoginService
import com.kdu.user.presentation.dto.LoginRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginController(private val loginService: LoginService) {

    @PostMapping("/api/login")
    fun login(loginRequestDto: LoginRequestDto): ResponseEntity<Boolean> {
        return ResponseEntity.ok(loginService.isAdmin(loginRequestDto))
    }
}