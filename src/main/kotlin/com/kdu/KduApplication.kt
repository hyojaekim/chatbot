package com.kdu

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KduApplication

fun main(args: Array<String>) {
	runApplication<KduApplication>(*args)
}
