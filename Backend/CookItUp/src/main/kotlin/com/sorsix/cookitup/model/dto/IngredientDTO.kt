package com.sorsix.cookitup.model.dto

import lombok.Data

@Data
data class IngredientDTO(
    val name: String,
    val description: String?=null
)
