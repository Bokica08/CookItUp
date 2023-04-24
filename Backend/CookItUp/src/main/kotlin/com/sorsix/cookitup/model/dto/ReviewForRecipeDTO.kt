package com.sorsix.cookitup.model.dto

import lombok.Data
import java.time.LocalDate

@Data
data class ReviewForRecipeDTO(
    val content: String?=null,
    val stars:Int,
    val username:String,
    val reviewedOn:LocalDate
)
