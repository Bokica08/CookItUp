package com.sorsix.cookitup.model.dto

import lombok.Data

@Data
data class ReviewDTO(
    val content: String?=null,
    val stars:Int,
)
