package com.kdu.common.resolver

import com.google.gson.JsonElement
import com.kdu.common.util.KduJsonParser
import com.kdu.user.presentation.dto.UserInputDataRequestDto
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

class UserInputDataArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType.isAssignableFrom(UserInputDataRequestDto::class.java)
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        val request: HttpServletRequest? = webRequest.getNativeRequest(HttpServletRequest::class.java)
        val jsonElement: JsonElement = KduJsonParser.toJsonElement(request!!)
        val userInputData: String = KduJsonParser.findParams(jsonElement, "text")
        return UserInputDataRequestDto(userInputData)
    }
}