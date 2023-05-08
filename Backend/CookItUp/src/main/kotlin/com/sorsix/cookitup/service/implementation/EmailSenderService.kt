package com.sorsix.cookitup.service.implementation

import org.springframework.mail.SimpleMailMessage
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.stereotype.Service


@Service
class EmailSenderService(private val mailSender: JavaMailSender) {
    fun sendSimpleEmail(
        toEmail: Array<String>,
        subject: String,
        body: String
    ) {
        for (email in toEmail) {
            val message = SimpleMailMessage()
            message.setFrom("noreplymail.cookitup@gmail.com")
            message.setTo(email)
            message.setText(body)
            message.setSubject(subject)
            mailSender.send(message)
        }
    }
}

