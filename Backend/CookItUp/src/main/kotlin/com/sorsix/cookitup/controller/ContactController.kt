package com.sorsix.cookitup.controller

import com.sorsix.cookitup.model.dto.ContactForm
import com.sorsix.cookitup.service.implementation.EmailSenderService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*


@CrossOrigin(origins = ["http://localhost:4200"], allowCredentials = "true", maxAge = 3600)
@RestController
@RequestMapping("/api/contact")
class ContactController(
    val mailSender: EmailSenderService
) {
    @PostMapping("")
    fun submitContactForm(@RequestBody form: ContactForm): ResponseEntity<Any>? {
        val message = "Message by : ${form.name} with email : ${form.email}\n${form.message}"
        val emails:Array<String> = arrayOf("bojantrpeski123@gmail.com","andrejtodorovski5@gmail.com")
        mailSender.sendSimpleEmail(emails,form.subject,message)
        val userEmail:Array<String> = arrayOf(form.email)
        val userMessage = """Your message was successfully sent. 
            |Thank you for your feedback. 
            |We will get back to you as soon as possible""".trimMargin()
        val userSubject = "Successfully sent feedback"
        mailSender.sendSimpleEmail(userEmail,userSubject,userMessage)
        return ResponseEntity.ok("Success")
    }
}