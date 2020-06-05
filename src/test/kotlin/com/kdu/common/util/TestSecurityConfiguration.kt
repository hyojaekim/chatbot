package com.kdu.common.util

import org.springframework.boot.test.context.TestConfiguration
import org.springframework.security.config.annotation.web.WebSecurityConfigurer
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@TestConfiguration
class TestSecurityConfiguration : WebSecurityConfigurer<WebSecurity?> {
    @Throws(Exception::class)
    override fun init(builder: WebSecurity?) {
        builder!!.ignoring().requestMatchers(
                AntPathRequestMatcher("/**"))
    }

    @Throws(Exception::class)
    override fun configure(builder: WebSecurity?) {
    }
}