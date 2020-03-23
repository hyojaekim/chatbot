package com.kdu.common.controller

import com.kdu.common.message.ResponseMessage
import org.springframework.core.env.Environment
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class EnvironmentController(private val environment: Environment) {

    @GetMapping("/api/profile")
    fun profile(): ResponseEntity<String> {
        return ResponseEntity.ok(Arrays.stream(environment.activeProfiles)
                .findFirst()
                .orElse(""))
    }

    @PostMapping("/api/hello")
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok(ResponseMessage.Builder()
                .simpleText("Hello World!")
                .build()
                .toString())
    }
}