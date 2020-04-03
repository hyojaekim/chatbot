package com.kdu.common.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory
import org.springframework.web.client.RestTemplate

@Configuration
class HttpConnectionConfig {

    @Bean
    fun getRestTemplate(): RestTemplate {
        val httpRequestFactory = HttpComponentsClientHttpRequestFactory()
        httpRequestFactory.setConnectTimeout(10000)
        httpRequestFactory.setReadTimeout(10000)
        return RestTemplate(httpRequestFactory)
    }
}