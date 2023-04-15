package com.sorsix.cookitup.model.dto

import lombok.Data

@Data
data class IngredientIsInRecipeDTO(
    val name:String,
    val quantity:Int,
    val measure: String
) {
}