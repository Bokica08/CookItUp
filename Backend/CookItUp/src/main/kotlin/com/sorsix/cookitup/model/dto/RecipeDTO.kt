package com.sorsix.cookitup.model.dto

import lombok.Data
import org.springframework.web.multipart.MultipartFile

@Data
data class RecipeDTO(
    val name:String,
    val description:String,
    val numPersons:Int,
    val difficultyLevel: String,
    val prepTime:Int,
    val categoryList: List<String>,
    val ingredientList: List<IngredientIsInRecipeDTO>,
)