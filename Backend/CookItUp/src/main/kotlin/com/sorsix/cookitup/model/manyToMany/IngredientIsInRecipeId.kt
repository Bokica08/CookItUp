package com.sorsix.cookitup.model.manyToMany

import lombok.Data
import java.io.Serializable

@Data
class IngredientIsInRecipeId(
    val ingredientId:Long,
    val recipeId:Long):Serializable{}