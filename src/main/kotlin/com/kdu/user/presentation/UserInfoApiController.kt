package com.kdu.user.presentation

import com.kdu.user.application.UserInfoService
import com.kdu.user.presentation.dto.UserInfoRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserInfoApiController(val userInfoService: UserInfoService) {

    @PostMapping("/api/user/info/campus")
    fun saveCampus(userInfoRequestDto: UserInfoRequestDto): ResponseEntity<Unit> {
        userInfoService.register(userInfoRequestDto)
        return ResponseEntity.ok().build()
    }
}