package com.sorsix.cookitup.model.dto

import com.sorsix.cookitup.model.enumeration.Role
import lombok.Data

@Data
data class CustomerInfoDTO (
     val firstname: String? = null,
     val lastname: String? = null,
     var username: String = "",
     val email: String? = null,
     var phoneNumber: String,
     var address: String,
     val role: Role,

)