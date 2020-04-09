package com.kdu.life.presentation

import com.kdu.common.message.BasicCard
import com.kdu.common.resolver.AddressArgumentResolver
import com.kdu.life.application.MaskService
import com.kdu.life.exception.MaskStockRequestFailException
import com.kdu.life.presentation.advice.MaskControllerAdvice
import com.kdu.util.JsonFactory
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.given
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
class MaskControllerMockTest {

    @Mock
    lateinit var maskService: MaskService

    @InjectMocks
    lateinit var maskController: MaskController

    lateinit var mockMvc: MockMvc

    @BeforeEach
    internal fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(maskController)
                .setCustomArgumentResolvers(AddressArgumentResolver())
                .setControllerAdvice(MaskControllerAdvice())
                .build()
    }

    @Test
    internal fun `정상적으로 마스크 재고 정보를 가져온다`() {
        given(maskService.findMaskStockInfo(any()))
                .willReturn(arrayListOf(BasicCard("testTitle", "content")))

        mockMvc.post("/api/mask/stock") {
            accept = MediaType.APPLICATION_JSON
            content = JsonFactory.createParams("address", "의정부역")
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.template.outputs[0].carousel.items[0].title") { value("testTitle") }
        }.andDo { print() }
    }

    @Test
    internal fun `마스크 재고 정보를 가져오는데 실패하는 경우 해당 메세지를 전송한다`() {
        given(maskService.findMaskStockInfo(any())).willThrow(MaskStockRequestFailException())

        mockMvc.post("/api/mask/stock") {
            accept = MediaType.APPLICATION_JSON
            content = JsonFactory.createParams("address", "의정부역")
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.template.outputs[0].simpleText.text") {
                value("마스크 재고 정보를 가져오는데 실패했습니다. 다시 한번 요청해주세요.")
            }
        }.andDo { print() }
    }
}