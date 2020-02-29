package com.kdu.user.application

import com.kdu.user.domain.User
import com.kdu.user.domain.UserRepository
import com.kdu.user.presentation.dto.UserInfoRequestDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserInternalService(val userRepository: UserRepository) {

    @Transactional
    fun save(userInfoRequestDto: UserInfoRequestDto): User {
        if (userRepository.existsByKakaoId(userInfoRequestDto.kakaoId)) {
            val savedUser = userRepository.findByKakaoId(userInfoRequestDto.kakaoId)
            savedUser.update(userInfoRequestDto.campus)
            return savedUser
        }
        return userRepository.save(User(userInfoRequestDto.kakaoId, userInfoRequestDto.campus))
    }
}