package com.kdu.user.presentation

import com.kdu.user.application.LoginService
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
import org.springframework.util.LinkedMultiValueMap

@ExtendWith(MockitoExtension::class)
internal class LoginControllerTest {

    @Mock
    lateinit var loginService: LoginService

    @InjectMocks
    lateinit var loginController: LoginController

    private lateinit var mockMvc: MockMvc

    @BeforeEach
    internal fun setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(loginController)
                .build()
    }

    @Test
    internal fun `로그인시 관리자인지 확인한다`() {
        whenever(loginService.isAdmin(any())).thenReturn(true)

        mockMvc.post("/api/login") {
            val formData = LinkedMultiValueMap<String, String>()
            formData.add("accessToken", "temp")
            params = formData
        }.andExpect {
            status { isOk }
            content { contentType(MediaType.APPLICATION_JSON) }
        }.andDo { print() }
    }
}