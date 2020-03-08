package com.kdu.user.application

import com.kdu.user.domain.Campus
import com.kdu.user.exception.NotFoundCampusException
import com.kdu.user.ui.dto.UserInfoRequestDto
import org.springframework.stereotype.Service

@Service
class UserInfoService(val userInternalService: UserInternalService) {

    fun register(userInfoRequestDto: UserInfoRequestDto) {
        if (userInfoRequestDto.campus == Campus.EMPTY) {
            throw NotFoundCampusException()
        }
        userInternalService.save(userInfoRequestDto)
    }
}