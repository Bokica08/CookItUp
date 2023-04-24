package com.sorsix.cookitup.model.dto

import com.sorsix.cookitup.model.enumeration.Role
import lombok.Data

@Data
data class RegisterDTO(
    var username: String,
    var password: String,
    var repeatedPassword: String,
    var firstName: String,
    var lastName: String,
    var role:Role,
    var email: String
)