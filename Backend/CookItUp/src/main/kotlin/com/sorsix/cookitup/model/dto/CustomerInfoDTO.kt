package com.sorsix.cookitup.model.dto

import com.sorsix.cookitup.model.enumeration.Role
import lombok.Data

@Data
data class CustomerInfoDTO (
    private val firstname: String? = null,
    private val lastname: String? = null,
    private var username: String = "",
    private val email: String? = null,
    private  var phoneNumber: String,
    private var address: String,
    private val role: Role,

){
}