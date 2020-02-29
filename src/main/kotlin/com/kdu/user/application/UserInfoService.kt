package com.kdu.user.application

import com.kdu.user.presentation.dto.UserInfoRequestDto
import org.springframework.stereotype.Service

@Service
class UserInfoService(val userInternalService: UserInternalService) {

    fun register(userInfoRequestDto: UserInfoRequestDto) {
        userInternalService.save(userInfoRequestDto)
    }
}