package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.Review
import com.sorsix.cookitup.model.dto.IngredientDTO

interface IngredientService {
    fun getAllIngredients():List<Ingredient>
    fun deleteAllByRecipe(recipe: Recipe) : Any
    fun save(ingredient: IngredientDTO):Ingredient

}