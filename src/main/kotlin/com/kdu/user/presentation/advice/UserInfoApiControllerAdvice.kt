package com.kdu.user.presentation.advice

import com.kdu.common.message.ResponseMessage
import com.kdu.user.exception.NotFoundCampusException
import com.kdu.user.exception.NotFoundUserException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class UserInfoApiControllerAdvice {

    @ExceptionHandler(NotFoundCampusException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNotFoundCampusException() {
    }

    @ExceptionHandler(NotFoundUserException::class)
    fun handleNotFoundUserException(): ResponseEntity<String> {
        return ResponseEntity.ok(ResponseMessage.Builder()
                .simpleText("캠퍼스를 설정하면 정상적으로 이용이 가능합니다.")
                .build()
                .toString())
    }
}