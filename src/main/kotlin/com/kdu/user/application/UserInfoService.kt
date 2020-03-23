package com.kdu.user.application

import com.kdu.food.domain.Cafeteria
import com.kdu.user.domain.Campus
import com.kdu.user.domain.User
import com.kdu.user.exception.NotFoundCampusException
import com.kdu.user.presentation.dto.KakaoInfoRequestDto
import com.kdu.user.presentation.dto.UserInfoRequestDto
import org.springframework.stereotype.Service

@Service
class UserInfoService(val userInternalService: UserInternalService) {

    fun register(userInfoRequestDto: UserInfoRequestDto) {
        if (userInfoRequestDto.campus == Campus.EMPTY) {
            throw NotFoundCampusException()
        }
        userInternalService.save(userInfoRequestDto)
    }

    fun cafeteriaButton(kakaoInfoRequestDto: KakaoInfoRequestDto): ArrayList<String> {
        val user: User = userInternalService.findByKakaoId(kakaoInfoRequestDto.kakaoId)
        val campus: Campus = user.campus

        return Cafeteria.findByCampus(campus)
    }
}