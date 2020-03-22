package com.kdu.food.ui.advice

import com.kdu.food.exception.CampusFoodCrawlingFailException
import com.kdu.food.exception.NotFoundCafeteriaException
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class CampusFoodControllerAdvice {

    @ExceptionHandler(CampusFoodCrawlingFailException::class, NotFoundCafeteriaException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleCampusFoodCrawlingFailException() {
    }
}