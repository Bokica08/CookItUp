package com.sorsix.cookitup.model.dto

import lombok.Data

@Data
data class RegisterDTO(
    var username: String,
    var password: String,
    var repeatedPassword: String,
    var firstName: String,
    var lastName: String,
    var email: String,
    var phoneNumber: String? = null,
    var address: String? = null,
) {
}