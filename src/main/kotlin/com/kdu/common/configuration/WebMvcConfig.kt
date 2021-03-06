package com.kdu.common.configuration

import com.kdu.common.resolver.*
import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebMvcConfig : WebMvcConfigurer {

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(UserInfoArgumentResolver())
        resolvers.add(MaskInfoArgumentResolver())
        resolvers.add(KakaoInfoArgumentResolver())
        resolvers.add(CampusFoodArgumentResolver())
        resolvers.add(SubwayInfoArgumentResolver())
        resolvers.add(AddressArgumentResolver())
        resolvers.add(QrCodeArgumentResolver())
        resolvers.add(UserInputDataArgumentResolver())
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/api/admin/**")
                .allowedOrigins("*")
        registry.addMapping("/api/login")
                .allowedOrigins("*")
    }
}