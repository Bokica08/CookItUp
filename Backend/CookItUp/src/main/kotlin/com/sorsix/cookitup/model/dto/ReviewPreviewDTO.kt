package com.sorsix.cookitup.model.dto

import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Recipe
import java.time.LocalDateTime
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne

data class ReviewPreviewDTO(
    val reviewId: Long,
    val content: String?=null,
    val stars:Int,
    val reviewedOn: LocalDateTime,
    val recipe: RecipePreviewDTO,
    val customer: Customer,
)
