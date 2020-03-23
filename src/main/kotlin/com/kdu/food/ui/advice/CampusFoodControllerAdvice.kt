package com.kdu.food.ui.advice

import com.kdu.common.message.ResponseMessage
import com.kdu.food.exception.CampusFoodCrawlingFailException
import com.kdu.food.exception.NotFoundCafeteriaException
import com.kdu.food.exception.NotFoundCampusFoodException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class CampusFoodControllerAdvice {

    @ExceptionHandler(CampusFoodCrawlingFailException::class, NotFoundCafeteriaException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleCampusFoodCrawlingFailException() {
    }

    @ExceptionHandler(NotFoundCampusFoodException::class)
    fun handleCampusFoodCrawlingFailException2(): ResponseEntity<String> {
        return ResponseEntity.ok(ResponseMessage.Builder()
                .simpleText("현재 메뉴가 정해지지 않았습니다.")
                .build()
                .toString())
    }
}