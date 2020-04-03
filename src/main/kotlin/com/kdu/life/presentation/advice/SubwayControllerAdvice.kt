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
                .simpleText("올바르지 않는 역이름 입니다.\nex) 잠실역(x) -> 잠실(o)")
                .build()
                .toString())
    }
}