package com.kdu.life.presentation.advice

import com.kdu.common.message.ResponseMessage
import com.kdu.life.exception.MaskStockRequestFailException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class MaskControllerAdvice {

    @ExceptionHandler(MaskStockRequestFailException::class)
    fun handleMaskStockRequestFailException(): ResponseEntity<String> {
        return ResponseEntity.ok(ResponseMessage.Builder()
                .simpleText("마스크 재고 정보를 가져오는데 실패했습니다. 다시 한번 요청해주세요.")
                .build()
                .toString())
    }
}