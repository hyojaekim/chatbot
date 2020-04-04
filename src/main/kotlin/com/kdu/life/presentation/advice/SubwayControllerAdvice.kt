package com.kdu.life.presentation.advice

import com.kdu.common.message.ResponseMessage
import com.kdu.life.exception.NotFoundSubwayException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class SubwayControllerAdvice {

    @ExceptionHandler(NotFoundSubwayException::class)
    fun handleNotFoundSubwayException(): ResponseEntity<String> {
        return ResponseEntity.ok(ResponseMessage.Builder()
                .simpleText("올바르지 않는 역이름 또는 서버가 인식하지 못해서 발생한 문제일 수 있습니다.\n\n다시 한번 요청해주세요.")
                .build()
                .toString())
    }
}