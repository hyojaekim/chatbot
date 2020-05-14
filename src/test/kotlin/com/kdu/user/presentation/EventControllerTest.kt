package com.kdu.user.presentation

import com.kdu.common.resolver.QrCodeArgumentResolver
import com.kdu.user.application.KduEventService
import com.kdu.user.exception.DuplicateKduEventException
import com.kdu.user.exception.EventCloseException
import com.kdu.user.exception.IncorrectEventRequestException
import com.kdu.user.presentation.advice.EventUserApiControllerAdvice
import com.kdu.util.JsonFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.jupiter.MockitoExtension
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.setup.MockMvcBuilders

@ExtendWith(MockitoExtension::class)
internal class EventControllerTest {

    @Mock
    private lateinit var kduEventService: KduEventService

    @InjectMocks
    private lateinit var kduEventController: EventController

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    internal fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(kduEventController)
                .setCustomArgumentResolvers(QrCodeArgumentResolver())
                .setControllerAdvice(EventUserApiControllerAdvice())
                .build()
    }

    @Test
    internal fun `정상적으로 당첨된다`() {
        whenever(kduEventService.getEventResult(any())).thenReturn("정상")

        val qrCodeData = "kdu event"
        val resultMessage = "정상"

        request(qrCodeData, resultMessage)
    }

    @Test
    internal fun `잘못된 QR 코드로 요청하는 경우 예외가 발생한다`() {
        whenever(kduEventService.getEventResult(any())).thenThrow(IncorrectEventRequestException())

        val qrCodeData = "fail"
        val resultMessage = "올바르지 않은 요청 입니다."

        request(qrCodeData, resultMessage)
    }

    @Test
    internal fun `이미 응모한 경우 예외가 발생한다`() {
        whenever(kduEventService.getEventResult(any())).thenThrow(DuplicateKduEventException())

        val qrCodeData = "kdu event"
        val resultMessage = "이미 응모한 사용자 입니다."

        request(qrCodeData, resultMessage)
    }

    @Test
    internal fun `모든 당첨자가 선정된 경우 이벤트가 끝난다`() {
        whenever(kduEventService.getEventResult(any())).thenThrow(EventCloseException())

        val qrCodeData = "kdu event"
        val resultMessage = "이벤트가 종료되었습니다."

        request(qrCodeData, resultMessage)
    }

    private fun request(qrCodeData: String, resultMessage: String) {
        mockMvc.post("/api/event") {
            accept = MediaType.APPLICATION_JSON
            content = JsonFactory.createParams("event", createRequestDto(qrCodeData))
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.template.outputs[0].simpleText.text") {
                value(resultMessage)
            }
        }.andDo { print() }
    }

    private fun createRequestDto(data: String): String {
        return "{\\\"barcodeData\\\":\\\"${data}\\\"}"
    }
}