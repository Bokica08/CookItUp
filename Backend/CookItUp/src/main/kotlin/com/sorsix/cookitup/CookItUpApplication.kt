package com.sorsix.cookitup

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.ServletComponentScan
import org.springframework.context.annotation.Bean
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.JavaMailSenderImpl


@SpringBootApplication
@ServletComponentScan
class CookItUpApplication

fun main(args: Array<String>) {
	runApplication<CookItUpApplication>(*args)
}

