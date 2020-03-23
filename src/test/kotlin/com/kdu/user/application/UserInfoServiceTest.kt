package com.kdu.user.application

import com.kdu.user.domain.Campus
import com.kdu.user.domain.User
import com.kdu.user.exception.NotFoundCampusException
import com.kdu.user.presentation.dto.KakaoInfoRequestDto
import com.kdu.user.presentation.dto.UserInfoRequestDto
import com.nhaarman.mockitokotlin2.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
class UserInfoServiceTest {

    @Mock
    private lateinit var userInternalService: UserInternalService

    @InjectMocks
    private lateinit var userInfoService: UserInfoService

    @Test
    internal fun `정상적으로 유저를 등록한다`() {
        whenever(userInternalService.save(any())).thenReturn(mock())

        userInfoService.register(UserInfoRequestDto("id", Campus.GOSEONG))

        verify(userInternalService, times(1)).save(any())
    }

    @Test
    internal fun `캠퍼스가 존재하지 않으면 예외가 발생한다`() {
        assertThrows(NotFoundCampusException::class.java) {
            userInfoService.register(UserInfoRequestDto("id", Campus.EMPTY))
        }
        verify(userInternalService, times(0)).save(any())
    }

    @Test
    internal fun `정상적으로 학교 식당 이름을 가져온다`() {
        val user = User("test", Campus.YANGJU)

        whenever(userInternalService.findByKakaoId(any())).thenReturn(user)

        val result = userInfoService.cafeteriaButton(KakaoInfoRequestDto("test"))

        assertThat(result).hasSize(2)
        for (cafeteria in result) {
            assertThat(cafeteria).contains("양주")
        }
    }
}