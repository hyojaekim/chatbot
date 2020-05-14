package com.kdu.user.application

import com.kdu.user.domain.EventUser
import com.kdu.user.domain.EventUserRepository
import com.kdu.user.exception.DuplicateKduEventException
import com.kdu.user.exception.EventCloseException
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
internal class KduEventInternalServiceTest {

    @Mock
    private lateinit var eventUserRepository: EventUserRepository

    @InjectMocks
    private lateinit var kduEventInternalService: KduEventInternalService

    @Test
    internal fun `정상적으로 당첨자인지 확인한다`() {
        val user = EventUser("testId")

        whenever(eventUserRepository.save(any<EventUser>())).thenReturn(user)

        assertThat(kduEventInternalService.isWinner(user.kakaoId)).isEqualTo(user.winner)
    }


    @Test
    internal fun `이미 이벤트에 응모한 유저는 예외가 발생한다`() {
        whenever(eventUserRepository.existsByKakaoId(any())).thenReturn(true)

        assertThrows<DuplicateKduEventException> {
            kduEventInternalService.isWinner("testId")
        }
    }

    @Test
    internal fun `당첨자가 모두 선정되면 이벤트를 종료한다`() {
        whenever(eventUserRepository.existsByKakaoId(any())).thenReturn(false)
        whenever(eventUserRepository.countAllByWinnerTrue()).thenReturn(2)

        assertThrows<EventCloseException> {
            kduEventInternalService.isWinner("testId")
        }
    }
}