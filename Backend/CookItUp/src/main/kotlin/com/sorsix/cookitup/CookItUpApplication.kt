package com.sorsix.cookitup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CookItUpApplication

fun main(args: Array<String>) {
	runApplication<CookItUpApplication>(*args)
}
