package com.kdu.user.presentation

import com.kdu.common.message.ResponseMessage
import com.kdu.user.application.UserInfoService
import com.kdu.user.presentation.dto.UserInfoRequestDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserInfoApiController(val userInfoService: UserInfoService) {

    @PostMapping("/api/user/info/campus")
    fun saveCampus(userInfoRequestDto: UserInfoRequestDto): ResponseEntity<String> {
        userInfoService.register(userInfoRequestDto)

        val campusName = userInfoRequestDto.campus.realName
        val responseMessage = ResponseMessage.Builder()
                .simpleText("$campusName 캠퍼스로 정상적으로 저장되었습니다.")
                .build()
        return ResponseEntity.ok(responseMessage.toString())
    }
}