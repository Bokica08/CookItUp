package com.sorsix.cookitup.model.dto

import lombok.Data

@Data
data class RecipeDTO(
    val name:String,
    val description:String,
    val numPersons:Int,
    val difficultyLevel: String,
    val prepTime:Int,
    val customerId: Long,
    val categoryList: List<String>,
    val ingredientList: List<IngredientIsInRecipeDTO>,
    )