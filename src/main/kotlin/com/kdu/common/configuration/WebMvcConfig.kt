package com.kdu.common.configuration

import com.kdu.common.resolver.CampusFoodArgumentResolver
import com.kdu.common.resolver.KakaoInfoArgumentResolver
import com.kdu.common.resolver.MaskInfoArgumentResolver
import com.kdu.common.resolver.UserInfoArgumentResolver
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(UserInfoArgumentResolver())
        resolvers.add(MaskInfoArgumentResolver())
        resolvers.add(KakaoInfoArgumentResolver())
        resolvers.add(CampusFoodArgumentResolver())
    }
}