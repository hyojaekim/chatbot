package com.kdu.common.configuration

import com.kdu.common.configuration.oauth2.CustomOAuth2ClientProperties
import com.kdu.common.configuration.oauth2.CustomOAuth2Provider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.oauth2.client.registration.ClientRegistration
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint
import org.springframework.security.web.csrf.CsrfFilter
import org.springframework.web.filter.CharacterEncodingFilter

@Configuration
@EnableWebSecurity
class SecurityConfig : WebSecurityConfigurerAdapter() {
    override fun configure(http: HttpSecurity) {
        http
                .authorizeRequests()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/admin/**").hasAnyRole("ADMIN")
                .antMatchers("/").permitAll()
                .anyRequest().permitAll()

                .and()
                .oauth2Login()
                .defaultSuccessUrl("/")

                .and()
                .headers().frameOptions().disable()

                .and()
                .exceptionHandling()
                .authenticationEntryPoint(LoginUrlAuthenticationEntryPoint("/"))

                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/")
                .deleteCookies("JSESSIONID")
                .invalidateHttpSession(true)

                .and()
                .addFilterAt(CharacterEncodingFilter(), CsrfFilter::class.java)
                .csrf().disable()
    }

    @Bean
    fun clientRegistrationRepository(customOAuth2ClientProperties: CustomOAuth2ClientProperties): InMemoryClientRegistrationRepository {
        val registrations = mutableListOf<ClientRegistration>()
        val customRegistrations = customOAuth2ClientProperties.registration

        for (customRegistration in customRegistrations) {
            when (customRegistration.key) {
                "kakao" -> registrations.add(CustomOAuth2Provider.KAKAO.getBuilder("kakao")
                        .clientId(customRegistration.value.clientId)
                        .clientSecret(customRegistration.value.clientSecret)
                        .jwkSetUri(customRegistration.value.jwkSetUri)
                        .build())
            }
        }
        return InMemoryClientRegistrationRepository(registrations)
    }
}