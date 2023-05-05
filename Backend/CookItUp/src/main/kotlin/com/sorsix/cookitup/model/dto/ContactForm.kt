package com.sorsix.cookitup.model.dto

data class ContactForm(
    val name: String,
    val email: String,
    val subject: String,
    val message: String,

) {
    override fun toString(): String {
        return "ContactForm(name='$name', email='$email', subject='$subject', message='$message')"
    }
}
