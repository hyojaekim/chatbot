package com.kdu.user.presentation.advice

import com.kdu.common.message.ResponseMessage
import com.kdu.user.exception.DuplicateKduEventException
import com.kdu.user.exception.EventCloseException
import com.kdu.user.exception.IncorrectEventRequestException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class EventUserApiControllerAdvice {

    @ExceptionHandler(DuplicateKduEventException::class)
    fun handleDuplicateEventUser(): ResponseEntity<String> {
        return ResponseEntity.ok(ResponseMessage.Builder()
                .simpleText("이미 응모한 사용자 입니다.")
                .build()
                .toString())
    }

    @ExceptionHandler(EventCloseException::class)
    fun handleEventClose(): ResponseEntity<String> {
        return ResponseEntity.ok(ResponseMessage.Builder()
                .simpleText("이벤트가 종료되었습니다.")
                .build()
                .toString())
    }

    @ExceptionHandler(IncorrectEventRequestException::class)
    fun handleIncorrectEvent(): ResponseEntity<String> {
        return ResponseEntity.ok(ResponseMessage.Builder()
                .simpleText("올바르지 않은 요청 입니다.")
                .build()
                .toString())
    }
}