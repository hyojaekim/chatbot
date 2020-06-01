package com.kdu.user.presentation.advice

import com.kdu.user.exception.LoginFailException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class LoginControllerAdvice {

    @ExceptionHandler(LoginFailException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleLoginFail() {
    }
}