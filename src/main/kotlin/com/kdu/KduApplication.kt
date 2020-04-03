package com.kdu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.context.annotation.PropertySource
import org.springframework.data.jpa.repository.config.EnableJpaAuditing

@EnableJpaAuditing
@SpringBootApplication
@PropertySource("/config.properties")
class KduApplication

private const val PROPERTIES = (
        "spring.config.location="
                + "classpath:application.yml,"
                + "/app/config/kdu-chatbot-service/real-application.yml"
        )

fun main(args: Array<String>) {
    SpringApplicationBuilder(KduApplication::class.java)
            .properties(PROPERTIES)
            .run(*args)
}
