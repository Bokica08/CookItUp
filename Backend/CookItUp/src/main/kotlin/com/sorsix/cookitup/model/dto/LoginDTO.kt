package com.sorsix.cookitup.model.dto

import lombok.Data

@Data
data class LoginDTO(
    val username: String,
    val password: String
)
