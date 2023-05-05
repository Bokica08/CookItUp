package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.dto.ContactForm
import org.slf4j.LoggerFactory
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.mail.MailSender
import org.springframework.mail.SimpleMailMessage
import java.util.*


@CrossOrigin(origins = ["http://localhost:4200"], allowCredentials = "true", maxAge = 3600)
@RestController
@RequestMapping("/api/contact")
class ContactController(
//private val mailSender: MailSender
) {
    private val LOGGER = LoggerFactory.getLogger(ContactController::class.java)
    @PostMapping("")
    fun submitContactForm(@RequestBody form: ContactForm): ResponseEntity<Any>? {
        val email = SimpleMailMessage()
        email.setFrom(form.email)
        email.setTo()
        email.setSubject(form.subject)
        email.setText(form.message)
        //mailSender.send(email)

        LOGGER.debug("New contact form submission: {}", form.toString())
        return ResponseEntity.ok("Thank you for contacting us!")
    }
}