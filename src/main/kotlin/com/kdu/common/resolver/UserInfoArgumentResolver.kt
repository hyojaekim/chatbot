package com.kdu.common.resolver

import com.google.gson.JsonElement
import com.kdu.common.util.KduJsonParser
import com.kdu.user.domain.Campus
import com.kdu.user.exception.NotFoundCampusException
import com.kdu.user.presentation.dto.UserInfoRequestDto
import org.springframework.core.MethodParameter
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer
import javax.servlet.http.HttpServletRequest

class UserInfoArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType.isAssignableFrom(UserInfoRequestDto::class.java)
    }

    override fun resolveArgument(parameter: MethodParameter, mavContainer: ModelAndViewContainer?, webRequest: NativeWebRequest, binderFactory: WebDataBinderFactory?): Any? {
        val request = webRequest.getNativeRequest(HttpServletRequest::class.java)
        val jsonElement = KduJsonParser.toJsonElement(request!!)

        val kakaoId = findKakaoId(jsonElement)
        val campus = findCampus(jsonElement)
        return UserInfoRequestDto(kakaoId, campus)
    }

    private fun findKakaoId(jsonElement: JsonElement) =
            jsonElement.asJsonObject.get("user").asJsonObject.get("id").asString

    private fun findCampus(jsonElement: JsonElement): Campus {
        val campusName = jsonElement.asJsonObject.get("value").asJsonObject.get("resolved").asString
        val campus = Campus.find(campusName)
        if (campus == Campus.EMPTY) {
            throw NotFoundCampusException()
        }
        return campus
    }
}