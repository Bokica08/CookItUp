package com.sorsix.cookitup.service

import com.sorsix.cookitup.model.Category
import com.sorsix.cookitup.model.Customer
import com.sorsix.cookitup.model.Ingredient
import com.sorsix.cookitup.model.Recipe
import com.sorsix.cookitup.model.dto.IngredientIsInRecipeDTO
import com.sorsix.cookitup.model.dto.RecipeDTO
import com.sorsix.cookitup.model.dto.RecipeInfoDTO
import com.sorsix.cookitup.model.dto.RecipePreviewDTO
import com.sorsix.cookitup.model.enumeration.DifficultyLevel

interface RecipeService {
    fun findAllByCustomer(customer: Customer) : List<RecipePreviewDTO>
    fun findAllByCategoryListContains(category: Category) : List<RecipePreviewDTO>
    fun findAllByDifficultyLevel(difficultyLevel: DifficultyLevel) : List<RecipePreviewDTO>
    fun findAllByNameContainingIgnoreCase(name: String) : List<RecipePreviewDTO>
    fun findAllByRecipe(recipe: Recipe) : List<Ingredient>
    fun findAllByIngredient(ingredient: Ingredient) : List<RecipeInfoDTO>
    fun getAll() : List<RecipePreviewDTO>
    fun save(recipeDTO: RecipeDTO, customer: Customer) : Recipe
    fun getDetailsForRecipe(id: Long) : RecipeInfoDTO
    fun getRecipeById(id: Long) : Recipe
    fun getNewestRecipes() : List<RecipePreviewDTO>
    fun getTopRatedRecipes() : List<RecipePreviewDTO>
    fun getMostViewedRecipes() : List<RecipePreviewDTO>
    fun getPreviewForRecipe(id: Long) : RecipePreviewDTO
    fun getNumberOfRecipes() : Long
    fun deleteRecipe(recipe: Recipe):Any
    fun getIngredientInRecipe(recipeId: Long, ingredientId: Long) : IngredientIsInRecipeDTO
    fun getFilteredRecipes(category: String?,inputText: String?,difficultyLevels: String?,prepTimes: String?):List<RecipePreviewDTO>
    fun getSimilarRecipes(id: Long) : List<RecipePreviewDTO>
    fun getIngredientsInRecipe(id: Long) : List<Ingredient>
}