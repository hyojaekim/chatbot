package com.kdu.common.resolver

import com.google.gson.JsonElement
import com.kdu.common.util.KduJsonParser
import com.kdu.life.ui.dto.MaskPurchaseInfoRequestDto
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import java.time.Year
import javax.servlet.http.HttpServletRequest

class MaskInfoArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType.isAssignableFrom(MaskPurchaseInfoRequestDto::class.java)
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        val request: HttpServletRequest? = webRequest.getNativeRequest(HttpServletRequest::class.java)
        val jsonElement: JsonElement = KduJsonParser.toJsonElement(request!!)

        val year = findYear(jsonElement)
        return MaskPurchaseInfoRequestDto(year)
    }

    fun findYear(jsonElement: JsonElement): Year {
        val year = KduJsonParser.find(jsonElement, listOf("userRequest", "utterance"))
        val convertYear = Integer.valueOf(year)
        return Year.of(convertYear)
    }
}