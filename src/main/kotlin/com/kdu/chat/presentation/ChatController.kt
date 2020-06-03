package com.kdu.chat.presentation

import com.kdu.chat.presentation.dto.ChatRequestDto
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
class ChatController {

    @MessageMapping("/api/chat/receive")
    @SendTo("/api/chat/send")
    fun send(chatRequestDto: ChatRequestDto): ChatRequestDto {
        return chatRequestDto
    }
}