package com.kdu.user.application

import com.kdu.user.exception.IncorrectEventRequestException
import com.kdu.user.presentation.dto.QrCodeRequestDto
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
internal class KduEventServiceTest {

    @Mock
    private lateinit var kduEventInternalService: KduEventInternalService

    @InjectMocks
    private lateinit var kduEventService: KduEventService

    @Test
    internal fun `당첨 메시지를 생성한다`() {
        val requestDto = QrCodeRequestDto("testID", "kdu event")

        whenever(kduEventInternalService.isWinner(any())).thenReturn(true)

        assertThat(kduEventService.getEventResult(requestDto)).isEqualTo("축하드립니다!!\n" +
                "당첨되셨습니다!!\n" +
                "해당 링크로 이동하셔서 학번, 이름, 번호를 입력해주세요.\n" +
                "\n" +
                "https://open.kakao.com/o/stu2eJbc")
    }

    @Test
    internal fun `당첨되지 않은 메시지를 생성한다`() {
        val requestDto = QrCodeRequestDto("testID", "kdu event")

        whenever(kduEventInternalService.isWinner(any())).thenReturn(false)

        assertThat(kduEventService.getEventResult(requestDto)).isEqualTo("참여해주셔서 감사합니다!\n\n아쉽게도 당첨되지 않았습니다ㅠ")
    }

    @Test
    internal fun `정상적인 QR 코드로 요청하지 않은 경우 예외가 발생한다`() {
        val requestDto = QrCodeRequestDto("testID", "false")

        assertThrows<IncorrectEventRequestException> {
            kduEventService.getEventResult(requestDto)
        }
    }
}