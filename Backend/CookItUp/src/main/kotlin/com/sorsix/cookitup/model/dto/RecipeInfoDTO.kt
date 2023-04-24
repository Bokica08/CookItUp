package com.sorsix.cookitup.model.dto

import com.sorsix.cookitup.model.Category
import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Image
import com.sorsix.cookitup.model.enumeration.DifficultyLevel
import lombok.Data
import java.time.LocalDate
import java.time.LocalDateTime

@Data
data class RecipeInfoDTO(
    val id:Long?=null,
    val name:String?=null,
    val description:String?=null,
    val numPersons:Int?=null,
    val difficultyLevel: DifficultyLevel?=null,
    val prepTime:Int?=null,
    val avRating:Double?=null,
    val viewCount:Int?=null,
    val createdOn: LocalDate?=null,
    val customer: Customer?=null,
    val categoryList: MutableList<Category>,
    val ingredientList: List<IngredientIsInRecipeDTO>,
    val imageList: List<Image>,
    val reviews: List<ReviewForRecipeDTO>
)
