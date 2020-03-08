package com.kdu.user.ui.advice

import com.kdu.user.exception.NotFoundCampusException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class UserInfoApiControllerAdvice {

    @ExceptionHandler(NotFoundCampusException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleNotFoundCampusException() {
    }
}