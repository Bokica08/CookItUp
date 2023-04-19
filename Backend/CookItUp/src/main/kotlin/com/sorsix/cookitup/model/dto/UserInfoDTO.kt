package com.sorsix.cookitup.model.dto

import com.sorsix.cookitup.model.enumeration.Role
import lombok.Data

@Data
data class UserInfoDTO(
     val firstname: String,
     val lastname: String,
     val username: String,
     val role: List<String> ){

}