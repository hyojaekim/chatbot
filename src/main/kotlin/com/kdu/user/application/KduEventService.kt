package com.kdu.user.application

import com.kdu.user.exception.IncorrectEventRequestException
import com.kdu.user.presentation.dto.QrCodeRequestDto
import org.springframework.stereotype.Service

@Service
class KduEventService(private val kduEventInternalService: KduEventInternalService) {

    fun getEventResult(qrCodeRequestDto: QrCodeRequestDto): String {
        checkCorrectRequest(qrCodeRequestDto.data)
        if (kduEventInternalService.isWinner(qrCodeRequestDto.kakaoId)) {
            return WINNER_MESSAGE
        }
        return LOSING_MESSAGE
    }

    private fun checkCorrectRequest(data: String) {
        if (!data.contains(QR_CODE_MESSAGE)) {
            throw IncorrectEventRequestException()
        }
    }

    companion object {
        private const val WINNER_MESSAGE = "축하드립니다!!\n당첨되셨습니다!!\n해당 링크로 이동하셔서 학번, 이름, 번호를 입력해주세요.\n\nhttps://open.kakao.com/o/stu2eJbc"
        private const val LOSING_MESSAGE = "참여해주셔서 감사합니다!\n\n아쉽게도 당첨되지 않았습니다ㅠ"
        private const val QR_CODE_MESSAGE = "kdu event"
    }
}