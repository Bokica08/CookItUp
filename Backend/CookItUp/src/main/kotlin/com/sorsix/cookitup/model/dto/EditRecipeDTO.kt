package com.sorsix.cookitup.model.dto

import com.sorsix.cookitup.model.Category
import lombok.Data

@Data
data class EditRecipeDTO(
    val name:String,
    val description:String,
    val numPersons:Int,
    val difficultyLevel: String,
    val prepTime:Int,
    val categoryList: List<Category>,
    val ingredientList: List<IngredientIsInRecipeDTO>,
)