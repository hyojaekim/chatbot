package com.kdu.common.resolver

import com.google.gson.JsonElement
import com.kdu.common.util.KduJsonParser
import com.kdu.food.application.dto.CampusFoodRequestDto
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import java.time.LocalDate
import javax.servlet.http.HttpServletRequest

class CampusFoodArgumentResolver : HandlerMethodArgumentResolver {
    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType.isAssignableFrom(CampusFoodRequestDto::class.java)
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        val request: HttpServletRequest? = webRequest.getNativeRequest(HttpServletRequest::class.java)
        val jsonElement: JsonElement = KduJsonParser.toJsonElement(request!!)
        val campusCode = KduJsonParser.findParams(jsonElement, "campusCode")

        return CampusFoodRequestDto(campusCode, LocalDate.now())
    }
}