package com.kdu.user.presentation

import com.kdu.common.message.ResponseMessage
import com.kdu.user.application.UserInputDataService
import com.kdu.user.presentation.dto.UserInputDataRequestDto
import com.kdu.user.presentation.dto.UserInputDataResponseDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user/data")
class UserInputDataApiController(private val userInputDataService: UserInputDataService) {

    @PostMapping
    fun saveUserInputData(userInputDataRequestDto: UserInputDataRequestDto): ResponseEntity<String> {
        userInputDataService.save(userInputDataRequestDto)
        return ResponseEntity.ok(ResponseMessage.Builder()
                .simpleText("정상적으로 저장되었습니다.")
                .build()
                .toString())
    }

    @GetMapping
    fun findAll(): ResponseEntity<List<UserInputDataResponseDto>> {
        val result: List<UserInputDataResponseDto> = userInputDataService.findAll()
        return ResponseEntity.ok(result)
    }
}