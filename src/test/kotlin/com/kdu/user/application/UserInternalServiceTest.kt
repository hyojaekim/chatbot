package com.kdu.user.application

import com.kdu.user.domain.Campus
import com.kdu.user.domain.User
import com.kdu.user.domain.UserRepository
import com.kdu.user.exception.NotFoundUserException
import com.kdu.user.presentation.dto.UserInfoRequestDto
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class UserInternalServiceTest {

    @Mock
    private lateinit var userRepository: UserRepository

    @InjectMocks
    private lateinit var userInternalService: UserInternalService

    @Test
    internal fun `정상적으로 캠퍼스를 저장한다`() {
        whenever(userRepository.existsByKakaoId(any())).thenReturn(false)
        whenever(userRepository.save(any<User>())).thenReturn(mock())

        userInternalService.save(UserInfoRequestDto("test", Campus.GOSEONG))

        verify(userRepository, times(1)).save(any<User>())
    }

    @Test
    internal fun `이미 존재하는 유저인 경우 캠퍼스를 변경한다`() {
        val user = User("testId", Campus.GOSEONG)

        whenever(userRepository.existsByKakaoId(any())).thenReturn(true)
        whenever(userRepository.findByKakaoId(any())).thenReturn(user)

        val result = userInternalService.save(UserInfoRequestDto(user.kakaoId, Campus.GOSEONG))

        assertThat(result.campus).isEqualTo(Campus.GOSEONG)
    }

    @Test
    internal fun `정상적으로 카카오 ID로 유저를 찾는다`() {
        val user = User("testId", Campus.GOSEONG)

        whenever(userRepository.findByKakaoId(any())).thenReturn(user)

        userInternalService.findByKakaoId("test")

        verify(userRepository, times(1)).findByKakaoId(any())
    }

    @Test
    internal fun `유효하지 않는 카카오 ID로 유저를 찾지 못한다`() {
        whenever(userRepository.findByKakaoId(any())).thenReturn(null)

        assertThrows<NotFoundUserException> {
            userInternalService.findByKakaoId("test")
        }
    }
}