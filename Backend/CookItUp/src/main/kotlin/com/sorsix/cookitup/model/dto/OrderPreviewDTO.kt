package com.sorsix.cookitup.model.dto

import com.sorsix.cookitup.model.Image
import com.sorsix.cookitup.model.enumeration.DifficultyLevel
import java.time.LocalDate

data class OrderPreviewDTO(
    val phoneNumber:String?=null,
    val address:String?=null,
    val numPersons:Int?=null,
    val recipe: RecipePreviewDTO?=null,
    val orderStatus: String?=null
)
