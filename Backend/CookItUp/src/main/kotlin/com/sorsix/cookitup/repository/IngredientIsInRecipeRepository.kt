package com.sorsix.cookitup.repository

import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.manyToMany.IngredientIsInRecipe
import com.sorsix.cookitup.model.manyToMany.IngredientIsInRecipeId
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface IngredientIsInRecipeRepository : JpaRepository<IngredientIsInRecipe, IngredientIsInRecipeId> {
    fun findAllByRecipe(recipe: Recipe) : List<IngredientIsInRecipe>
    fun findAllByIngredient(ingredient: Ingredient) : List<IngredientIsInRecipe>
}