package com.kdu.life.presentation

import com.kdu.common.message.BasicCard
import com.kdu.common.resolver.SubwayInfoArgumentResolver
import com.kdu.life.application.SubwayService
import com.kdu.life.exception.NotFoundSubwayException
import com.kdu.life.presentation.advice.SubwayControllerAdvice
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
internal class SubwayControllerTest {

    @Mock
    lateinit var subwayService: SubwayService

    @InjectMocks
    lateinit var subwayController: SubwayController

    lateinit var mockMvc: MockMvc

    @BeforeEach
    internal fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(subwayController)
                .setCustomArgumentResolvers(SubwayInfoArgumentResolver())
                .setControllerAdvice(SubwayControllerAdvice())
                .build()
    }

    @Test
    internal fun `정상적으로 지하철 도착정보를 가져온다`() {
        given(subwayService.find(any()))
                .willReturn(arrayListOf(BasicCard("testTitle", "content")))

        mockMvc.post("/api/subway") {
            accept = MediaType.APPLICATION_JSON
            content = JsonFactory.createParams("station", "의정부")
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.template.outputs[0].carousel.items[0].title") { value("testTitle") }
        }.andDo {
            print()
        }
    }

    @Test
    internal fun `올바르지 않은 지하철역이라면 예외가 발생한다`() {
        given(subwayService.find(any())).willThrow(NotFoundSubwayException())

        mockMvc.post("/api/subway") {
            accept = MediaType.APPLICATION_JSON
            content = JsonFactory.createParams("station", "의정부")
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
            jsonPath("$.template.outputs[0].simpleText.text") {
                value("올바르지 않는 역이름 또는 서버가 인식하지 못해서 발생한 문제일 수 있습니다.\n\n다시 한번 요청해주세요.")
            }
        }.andDo {
            print()
        }
    }
}